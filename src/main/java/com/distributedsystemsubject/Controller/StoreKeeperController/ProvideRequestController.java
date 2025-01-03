package com.distributedsystemsubject.Controller.StoreKeeperController;

import com.distributedsystemsubject.Dto.Request.ProvideMaterialRequest;
import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Service.StoreKeeperService.KafkaProducerStoreKeeperService;
import com.distributedsystemsubject.Service.StoreKeeperService.ProvideRequestService;
import com.distributedsystemsubject.Service.TeacherService.ManageProvideRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/storekeeper/provide")
public class ProvideRequestController {
    @Autowired
    private ProvideRequestService provideRequestService;
    @Autowired
    private KafkaProducerStoreKeeperService kafkaProducerStoreKeeperService;
    @Value("${kafka.topic.name2}")
    private String provideTopic;
    @Value("${spring.data.mongodb.ProvideType}")
    private String type;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getRequesterName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSupplyRequest(@RequestBody ProvideMaterialRequest request) {
        if (request.getMaterials() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        request.setStorekeeperName(getRequesterName());
        request.setDate(new Date());
        request.setTopicName(provideTopic);
        request.setType(type);
        request.setSource("67613d7bd6d5e23a7d90a609");
        try {
            String message = objectMapper.writeValueAsString(request);
            System.out.println("Sending message to Kafka: " + message);
            kafkaProducerStoreKeeperService.sendMessage(provideTopic, getRequesterName(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Request sent successfully");
    }

    @GetMapping("/view")
    public ResponseEntity<?> getProvideRequest() {
        List<MaterialProvide> requests = provideRequestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }
}
