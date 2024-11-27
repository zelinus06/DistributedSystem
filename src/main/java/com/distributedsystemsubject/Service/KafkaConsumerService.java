package com.distributedsystemsubject.Service;

import com.distributedsystemsubject.Dto.Request.MaterialSupplyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topic.name1}", groupId = "group_id")
    public void listen(String materialSupplyRequest) {
        System.out.println("Received MaterialSupplyRequest: " + materialSupplyRequest);
    }

    private void processMaterialSupplyRequest(MaterialSupplyRequest request) {
        System.out.println("Processing MaterialSupplyRequest for requester: " + request.getRequesterName());
    }
}
