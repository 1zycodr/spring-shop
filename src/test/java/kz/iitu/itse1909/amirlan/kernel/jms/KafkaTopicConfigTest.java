package kz.iitu.itse1909.amirlan.kernel.jms;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaAdmin;

class KafkaTopicConfigTest {
    KafkaTopicConfig kafkaTopicConfig = new KafkaTopicConfig();

    @Test
    void testKafkaAdmin() {
        KafkaAdmin result = kafkaTopicConfig.kafkaAdmin();
        Assertions.assertNotNull(result);
    }

    @Test
    void testTopic1() {
        NewTopic result = kafkaTopicConfig.topic1();
        Assertions.assertNotNull(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme