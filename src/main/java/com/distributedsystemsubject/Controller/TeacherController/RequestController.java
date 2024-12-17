package com.distributedsystemsubject.Controller.TeacherController;

import com.distributedsystemsubject.Dto.Request.MaterialSupplyRequest;
import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Service.TeacherService.KafkaProducerService;
import com.distributedsystemsubject.Service.TeacherService.ManageProvideRequestService;
import com.distributedsystemsubject.Service.TeacherService.ManageSupplyRequestService;
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
import java.util.List;

@RestController
@RequestMapping("/api/teacher/request")
public class RequestController {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private ManageSupplyRequestService manageSupplyRequestService;
    @Autowired
    private ManageProvideRequestService manageProvideRequestService;
    @Value("${kafka.topic.name1}")
    String requestTopic;
    @Value("${spring.data.mongodb.SupplyType}")
    String type;
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
    public ResponseEntity<String> createSupplyRequest(@RequestBody MaterialSupplyRequest request) {
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
            kafkaProducerService.sendMessage(requestTopic, getRequesterName(), message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Request sent to Kafka");
    }

    @GetMapping("/view")
    public Page<MaterialSupply> getMaterialSupplyRequests(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        String requesterName = getRequesterName();
        return manageSupplyRequestService.getAllRequests(requesterName, page, size);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<MaterialSupply> getMaterialSupplyRequestById(@PathVariable String id) {
        String requesterName = getRequesterName();
        MaterialSupply materialSupply = manageSupplyRequestService.getRequestById(requesterName, id);
        return ResponseEntity.ok(materialSupply);
    }

    @PostMapping("/view/{id}/update")
    public ResponseEntity<String> updateRequest(@RequestBody MaterialSupply updatedRequest) {
        try {
            String requesterName = getRequesterName();
            manageSupplyRequestService.updateMaterialRequest(requesterName, updatedRequest);
            return ResponseEntity.ok("updatedRequest");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing material supply request: " + e.getMessage());
        }
    }

    @GetMapping("/provideRequest")
    public ResponseEntity<?> getAllMaterialProvideRequestByTeacherName() {
        List<MaterialProvide> materialProvide = manageProvideRequestService.getAllRequest(getRequesterName());
        return ResponseEntity.ok(materialProvide);
    }

    @PostMapping("/provideRequest/{id}")
    public ResponseEntity<?> confirmProvideRequestById(@PathVariable String id) {
        manageProvideRequestService.confirmRequestById(id);
        return ResponseEntity.ok("Request confirmed");
    }

}
