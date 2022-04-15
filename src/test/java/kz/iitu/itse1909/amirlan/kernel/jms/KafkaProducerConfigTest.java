package kz.iitu.itse1909.amirlan.kernel.jms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

class KafkaProducerConfigTest {
    KafkaProducerConfig kafkaProducerConfig = new KafkaProducerConfig();

    @Test
    void testProducerFactory() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            ProducerFactory<String, String> result = kafkaProducerConfig.producerFactory();
        });
    }

    @Test
    void testKafkaTemplate() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            KafkaTemplate<String, String> result = kafkaProducerConfig.kafkaTemplate();
        });
    }
}
