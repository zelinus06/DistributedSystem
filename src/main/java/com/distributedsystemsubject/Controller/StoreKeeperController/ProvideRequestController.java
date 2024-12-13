package com.distributedsystemsubject.Controller.StoreKeeperController;

import com.distributedsystemsubject.Dto.Request.ProvideMaterialRequest;
import com.distributedsystemsubject.Service.StoreKeeperService.KafkaProducerStoreKeeperService;
import com.distributedsystemsubject.Service.StoreKeeperService.ManageProvideRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/storekeeper/provide")
public class ProvideRequestController {
    @Autowired
    private ManageProvideRequestService manageProvideRequestService;
    @Autowired
    private KafkaProducerStoreKeeperService kafkaProducerStoreKeeperService;
    @Value("${kafka.topic.name2}")
    private String provideTopic;
    @Value("${spring.data.mongodb.ProvideType}")
    private String type;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/create")
    public ResponseEntity<String> createSupplyRequest(@RequestBody ProvideMaterialRequest request) {
        if ( request.getMaterials() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        String topicName = request.getTopicName();
        String name= request.getStorekeeperName();
        request.setStorekeeperName(name);
        request.setDate(new Date());
        request.setTopicName(provideTopic);
        request.setType(type);
        try {
            String message = objectMapper.writeValueAsString(request);
            System.out.println("Sending message to Kafka: " + message);
            kafkaProducerStoreKeeperService.sendMessage(topicName, name, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Request sent to Kafka");
    }
}
