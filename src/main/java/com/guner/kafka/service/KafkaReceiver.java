package com.guner.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listenGroupGroup1(String message) {
        System.out.println("-----   Received Message in group group-1: " + message);
    }

    //@KafkaListener(topics = "topic-1, topic-2", groupId = "group-1")
    @KafkaListener(topics = "topic-2", groupId = "group-1")
    public void listenMultipleTopicsGroupGroup1(String message) {
        System.out.println("-----   Received Message on topic-2 in group group-1: " + message);
    }
}
