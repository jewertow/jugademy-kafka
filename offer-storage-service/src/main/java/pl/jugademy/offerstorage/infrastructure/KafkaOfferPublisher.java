package pl.jugademy.offerstorage.infrastructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.jugademy.offerstorage.domain.OfferEvent;
import pl.jugademy.offerstorage.domain.OfferPublisher;
import pl.jugademy.offerstorage.infrastructure.config.KafkaProducerProperties;

@Component
public class KafkaOfferPublisher implements OfferPublisher {

    private final Logger logger = LoggerFactory.getLogger(KafkaOfferPublisher.class);

    private final KafkaProducer<byte[], byte[]> kafkaProducer;
    private final KafkaProducerProperties properties;
    private final ObjectMapper objectMapper;

    public KafkaOfferPublisher(
            KafkaProducer<byte[], byte[]> kafkaProducer,
            KafkaProducerProperties properties,
            ObjectMapper objectMapper
    ) {
        this.kafkaProducer = kafkaProducer;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(OfferEvent offerEvent) {
        try {
            byte[] event = objectMapper.writeValueAsBytes(offerEvent);
            kafkaProducer.send(new ProducerRecord<byte[], byte[]>(properties.getTopic(), event)).get();
            logger.info("Successfully sent event {}, topic: {}", offerEvent, properties.getTopic());
        } catch (JsonProcessingException e) {
            logger.error("Failed to publish event due to serialization error: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            logger.error("Failed to publish event due to {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
