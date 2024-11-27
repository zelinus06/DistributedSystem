package com.distributedsystemsubject.Controller;

import com.distributedsystemsubject.Dto.Request.MaterialSupplyRequest;
import com.distributedsystemsubject.Service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supply-request")
public class MaterialSupplyRequestController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping
    public ResponseEntity<String> createSupplyRequest(@RequestBody MaterialSupplyRequest request) {
        if (request.getRequesterName() == null || request.getMaterials() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        String message = request.toString();
        String topicName= request.getTopicName();
        kafkaProducerService.sendMessage(topicName, request.getRequesterName(), message);
        return ResponseEntity.ok("Request sent to Kafka");
    }
}
