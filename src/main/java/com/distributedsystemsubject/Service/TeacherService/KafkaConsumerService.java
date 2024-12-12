package com.distributedsystemsubject.Service.TeacherService;

import com.distributedsystemsubject.Service.StoreKeeperService.MaterialStorageService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private MaterialStorageService materialStorageService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "${kafka.topic.name2}", groupId = "group_id")
    public void listen(String provideRequest, Acknowledgment acknowledgment) {
        try {
            JsonNode jsonNode = objectMapper.readTree(provideRequest);
            ((ObjectNode) jsonNode).put("status", "pending");
            String updatedRequest = jsonNode.toString();
            materialStorageService.saveRequest(updatedRequest);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
