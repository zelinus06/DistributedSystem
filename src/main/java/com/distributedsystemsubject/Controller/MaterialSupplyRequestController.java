package com.distributedsystemsubject.Controller;

import com.distributedsystemsubject.Dto.Request.MaterialSupplyRequest;
import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Service.KafkaProducerService;
import com.distributedsystemsubject.Service.MaterialSupplyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/supply-request")
public class MaterialSupplyRequestController {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private MaterialSupplyService materialSupplyService;

    @PostMapping
    public ResponseEntity<String> createSupplyRequest(@RequestBody MaterialSupplyRequest request) {
        if (request.getRequesterName() == null || request.getMaterials() == null) {
            return ResponseEntity.badRequest().body("Invalid request data");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String topicName = request.getTopicName();
        String requesterName = request.getRequesterName();
        try {
            String message = objectMapper.writeValueAsString(request);
            System.out.println("Sending message to Kafka: " + message);
            kafkaProducerService.sendMessage(topicName, requesterName, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Request sent to Kafka");
    }

    @GetMapping
    public Page<MaterialSupply> getMaterialSupplyRequests(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return materialSupplyService.getAllRequests(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialSupply> getMaterialSupplyRequestById(@PathVariable String id) {
        MaterialSupply request = materialSupplyService.getRequestById(id);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(request);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<String> approveMaterialSupplyRequest(@PathVariable String id,
                                                               @RequestParam Boolean approved,
                                                               @RequestParam String name,
                                                               @RequestParam(required = false) String note) {
        try {
            String result = materialSupplyService.approveRequest(id, approved, note, name);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid request data");
        }
    }

}
