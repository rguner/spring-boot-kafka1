package com.guner.kafka.service;

import com.guner.kafka.config.KafkaTopicConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(KafkaTopicConfig.TOPIC_NAME, msg);
    }

    public void sendMessageWithCompletableFuture(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaTopicConfig.TOPIC_NAME, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }

    @PostConstruct
    public void sendMessagesToKafka() {
        sendMessageWithCompletableFuture("hello " + LocalDateTime.now());
    }
}