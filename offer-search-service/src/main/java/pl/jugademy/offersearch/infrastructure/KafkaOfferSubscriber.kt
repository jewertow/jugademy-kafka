package pl.jugademy.offersearch.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory
import pl.jugademy.offersearch.domain.OfferEvent
import pl.jugademy.offersearch.domain.OfferSubscriber
import java.util.concurrent.ExecutorService

class KafkaOfferSubscriber(
        private val consumer: KafkaConsumer<ByteArray, ByteArray>,
        private val topic: String,
        private val mapper: ObjectMapper,
        private val executor: ExecutorService
) : OfferSubscriber {

    override fun subscribe(consume: (offer: OfferEvent) -> Unit) {
        executor.execute {
            consumer.subscribe(setOf(topic))
            while (true) {
                val records = consumer.poll(100)
                records.forEach { event ->
                    logger.info("Received event with offset ${event.offset()}")
                    val offerEvent = mapper.readValue(event.value(), OfferEvent::class.java)
                    consume(offerEvent)
                }
            }
        }
    }

    companion object {
        val logger = LoggerFactory.getLogger(KafkaOfferSubscriber::class.java)!!
    }
}
