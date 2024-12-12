package com.distributedsystemsubject.Controller.TeacherController;

import com.distributedsystemsubject.Dto.Request.MaterialSupplyRequest;
import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Service.TeacherService.KafkaProducerService;
import com.distributedsystemsubject.Service.TeacherService.ManageRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/teacher/request")
public class MaterialRequestController {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private ManageRequestService manageRequestService;
    @Value("${kafka.topic.name1}")
    String requestTopic;
    @Value("${spring.data.mongodb.SupplyType}")
    String type;

    public String getRequesterName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSupplyRequest(@RequestBody MaterialSupplyRequest request) {
        if (request.getMaterials() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String topicName = request.getTopicName();
        String requesterName = request.getRequesterName();
        request.setRequesterName(requesterName);
        request.setDate(new Date());
        request.setTopicName(requestTopic);
        request.setType(type);
        try {
            String message = objectMapper.writeValueAsString(request);
            System.out.println("Sending message to Kafka: " + message);
            kafkaProducerService.sendMessage(topicName, requesterName, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Request sent to Kafka");
    }

    @GetMapping("/view")
    public Page<MaterialSupply> getMaterialSupplyRequests(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        String requesterName = getRequesterName();
        return manageRequestService.getAllRequests(requesterName, page, size);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<MaterialSupply> getMaterialSupplyRequestById(@PathVariable String id) {
        String requesterName = getRequesterName();
        MaterialSupply materialSupply = manageRequestService.getRequestById(requesterName, id);
        return ResponseEntity.ok(materialSupply);
    }

    @PostMapping("/view/{id}/update")
    public ResponseEntity<String> updateRequest(@RequestBody MaterialSupply updatedRequest) {
        try {
            String requesterName = getRequesterName();
            manageRequestService.updateMaterialRequest(requesterName, updatedRequest);
            return ResponseEntity.ok("updatedRequest");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing material supply request: " + e.getMessage());
        }
    }

}
