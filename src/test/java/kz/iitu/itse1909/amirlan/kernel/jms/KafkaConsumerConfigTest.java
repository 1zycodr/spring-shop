package kz.iitu.itse1909.amirlan.kernel.jms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

class KafkaConsumerConfigTest {
    KafkaConsumerConfig kafkaConsumerConfig = new KafkaConsumerConfig();

    @Test
    void testConsumerFactory() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            ConsumerFactory<String, String> result = kafkaConsumerConfig.consumerFactory();
        });
    }

    @Test
    void testKafkaListenerContainerFactory() {
        Assertions.assertThrowsExactly(NullPointerException.class, () -> {
            ConcurrentKafkaListenerContainerFactory<String, String> result = kafkaConsumerConfig.kafkaListenerContainerFactory();
        });
    }
}
