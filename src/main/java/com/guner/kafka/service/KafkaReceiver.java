package com.guner.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listenGroupGroup1(String message) {
        System.out.println("-----   Received Message in group group-1: " + message);
    }

    @KafkaListener(topics = "topic-1", groupId = "group-2")
    public void listenGroupGroup2(String message) {
        System.out.println("-----   Received Message in group group-2: " + message);
        throw new RuntimeException("Receive Exception to test retry mechanism");
    }

    //@KafkaListener(topics = "topic-1, topic-2", groupId = "group-1")
    @KafkaListener(topics = "topic-2", groupId = "group-1")
    public void listenMultipleTopicsGroupGroup1(String message) {
        System.out.println("-----   Received Message on topic-2 in group group-1: " + message);
    }


    /*
    @KafkaListener(topics = "topic-1", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("-----   Received Message: " + message);
    }
     */

}
