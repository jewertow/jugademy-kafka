package pl.jugademy.offersearch.infrastructure

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import java.util.Properties

class KafkaConsumerFactory {
    companion object {
        fun provide(config: Config): KafkaConsumer<ByteArray, ByteArray> {
            val props = Properties()
            props["bootstrap.servers"] = config.getProperty<String>("bootstrapServers")
            props["group.id"] = config.getProperty<String>("consumerGroup")
            props["key.deserializer"] = ByteArrayDeserializer::class.java.name
            props["value.deserializer"] = ByteArrayDeserializer::class.java.name
            return KafkaConsumer(props)
        }
    }
}
