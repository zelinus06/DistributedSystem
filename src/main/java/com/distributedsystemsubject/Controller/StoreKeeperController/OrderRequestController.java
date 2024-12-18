package com.distributedsystemsubject.Controller.StoreKeeperController;

import com.distributedsystemsubject.Dto.Request.OrderMaterialRequest;
import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Service.StoreKeeperService.KafkaProducerStoreKeeperService;
import com.distributedsystemsubject.Service.StoreKeeperService.ManagerOrderRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/storekeeper/order")
public class OrderRequestController {

    @Value("${kafka.topic.name3}")
    String requestTopic;
    @Value("${spring.data.mongodb.OrderType}")
    String type;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private KafkaProducerStoreKeeperService KafkaProducerStoreKeeperService;
    @Autowired
    private ManagerOrderRequestService manageSupplyRequestService;

    public String getRequesterName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrderRequest(@RequestBody OrderMaterialRequest request) {
        if (request.getMaterials() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        request.setRequesterName(getRequesterName());
        request.setDate(new Date());
        request.setTopicName(requestTopic);
        request.setType(type);
        try {
            String message = objectMapper.writeValueAsString(request);
            System.out.println("Sending message to Kafka: " + message);
            KafkaProducerStoreKeeperService.sendMessage(requestTopic, getRequesterName(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Request sent to Kafka");
    }

    @GetMapping("/view")
    public ResponseEntity<?> getMaterialSupplyRequests() {
        return ResponseEntity.ok(manageSupplyRequestService.getAllRequests());
    }
}
