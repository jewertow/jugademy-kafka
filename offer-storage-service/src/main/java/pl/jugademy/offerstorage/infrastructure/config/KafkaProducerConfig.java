package pl.jugademy.offerstorage.infrastructure.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public KafkaProducer<byte[], byte[]> kafkaProducer(KafkaProducerProperties properties) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrap());
        props.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put(REQUEST_TIMEOUT_MS_CONFIG, 500);
        return new KafkaProducer<>(props);
    }
}
