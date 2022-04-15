package kz.iitu.itse1909.amirlan.application.user.config;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
public class JmsListener {

    @KafkaListener(topics = "userstopic", errorHandler = "", groupId = "group_id")
    public void listener(String message) {
        System.out.println(message);
    }
}
