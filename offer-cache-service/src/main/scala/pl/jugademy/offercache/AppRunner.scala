package pl.jugademy.offercache

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.slf4j.LoggerFactory
import pl.jugademy.offercache.api.OffersEndpoint
import pl.jugademy.offercache.domain.OfferFacade
import pl.jugademy.offercache.infrastructure.{
  Config,
  KafkaConsumerFactory,
  KafkaOfferSubscriber,
  MongoDbOfferRepository,
  MongodbOffersCollectionFactory
}

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object AppRunner extends App {

  private val logger = LoggerFactory.getLogger("AppRunner")

  implicit val system: ActorSystem = ActorSystem("offer-cache-service")
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val config = Config.load()

  val offersCollection = MongodbOffersCollectionFactory.provide(config)
  val offerRepository = new MongoDbOfferRepository(offersCollection)
  val offerFacade = new OfferFacade(offerRepository)
  val offerEndpoint = new OffersEndpoint(offerFacade)
  val objectMapper = new ObjectMapper()
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule())

  val kafkaConsumer = KafkaConsumerFactory.provide(config)
  val offerSubscriber = new KafkaOfferSubscriber(kafkaConsumer, config.getProperty("topic"), objectMapper)
  offerSubscriber.subscribe { event =>
    offerFacade.consumeOfferEvent(event)
  }

  val host = "0.0.0.0"
  val port = 8080
  val api = offerEndpoint.getOfferRoute

  Http().bindAndHandle(api, host, port)
    .map { serverBinding =>
      logger.info(s"OfferEndpoint is running at ${serverBinding.localAddress}")
    }
    .onComplete {
      case Success(_) =>
      case Failure(e) =>
        logger.error(s"Failed to bind server due to $e")
        system.terminate()
    }
}
