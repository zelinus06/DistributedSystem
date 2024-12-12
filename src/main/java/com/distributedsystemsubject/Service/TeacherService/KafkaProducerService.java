package com.distributedsystemsubject.Service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessage(String topicName, String key, String message) {
        kafkaTemplate.send(topicName, key, message);
    }
}
