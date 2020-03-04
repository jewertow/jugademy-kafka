package pl.jugademy.offersearch

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory
import pl.jugademy.offersearch.domain.OfferSearchFacade
import pl.jugademy.offersearch.infrastructure.*
import java.util.concurrent.Executors

class App {
    companion object {
        val logger = LoggerFactory.getLogger(App::class.java)!!
    }
}

fun main(args: Array<String>) {
    val logger = App.logger

    val config = Config.load()
    val elasticClient = ElasticSearchClientFactory.provide(config)
    val objectMapper = ObjectMapper()
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())
    val offerRepository = ElasticSearchOfferRepository(elasticClient, objectMapper)
    val offerSearchFacade = OfferSearchFacade(offerRepository)
    val offerSubscriber = KafkaOfferSubscriber(
            consumer = KafkaConsumerFactory.provide(config),
            topic = config.getProperty("topic"),
            mapper = objectMapper,
            executor = Executors.newSingleThreadExecutor()
    )

    offerSubscriber.subscribe {
        offerSearchFacade.consumeOfferEvent(it)
    }

    embeddedServer(Netty, 8080) {
        logger.info("Server is running...")
        routing {
            get("/offers") {
                val query = call.request.queryParameters["q"] ?: ""
                logger.info("Received request to /offers?$query")
                val offers = offerSearchFacade.findByNameOrDescription(query)
                val jsonOffers = objectMapper.writeValueAsString(offers)
                call.respond(HttpStatusCode.OK, jsonOffers)
            }
        }
    }.start(wait = true)
}
