package pl.jugademy.offercache.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory
import pl.jugademy.offercache.domain.{OfferEvent, OfferSubscriber}

import scala.concurrent.ExecutionContext
import collection.JavaConverters._

class KafkaOfferSubscriber(
  consumer: KafkaConsumer[Array[Byte], Array[Byte]],
  topic: String,
  mapper: ObjectMapper
)(implicit ec: ExecutionContext) extends OfferSubscriber {

  private val logger = LoggerFactory.getLogger(classOf[KafkaOfferSubscriber].getName)

  override def subscribe(consume: OfferEvent => Unit): Unit =
    ec.execute {
      () => {
        consumer.subscribe(Set(topic).asJava)
        while (true) {
          val records = consumer.poll(100)
          records.forEach { rec =>
            logger.info(s"Received event with offset ${rec.offset()}")
            try {
              val offerEvent = mapper.readValue(rec.value(), classOf[OfferEvent])
              consume(offerEvent)
            } catch {
              case e: Exception =>
                logger.error(s"Could not deserialize event with offset ${rec.offset()} due to ${e.getMessage}", e)
            }
          }
        }
      }
    }
}
