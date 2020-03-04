package pl.jugademy.offercache.infrastructure

import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.ByteArrayDeserializer

object KafkaConsumerFactory {
  def provide(config: Config): KafkaConsumer[Array[Byte], Array[Byte]] = {
    val props = new Properties()
    props.put("bootstrap.servers", config.getProperty("bootstrapServers"))
    props.put("group.id", config.getProperty("consumerGroup"))
    props.put("key.deserializer", classOf[ByteArrayDeserializer].getName)
    props.put("value.deserializer", classOf[ByteArrayDeserializer].getName)
    new KafkaConsumer(props)
  }
}
