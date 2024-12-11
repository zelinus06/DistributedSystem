package com.distributedsystemsubject.Service;

import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Repository.MaterialSupplyRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MaterialSupplyService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MaterialSupplyRepo materialSupplyRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public Page<MaterialSupply> getAllRequests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return materialSupplyRepository.findAll(pageable);
    }

    public MaterialSupply getRequestById(String id) {
        return materialSupplyRepository.findById(id).orElse(null);
    }

    public String approveRequest(String id, Boolean approved, String note, String name) throws JsonProcessingException {
        MaterialSupply request = materialSupplyRepository.findById(id).orElse(null);
        if (request == null) {
            throw new RuntimeException("Request not found");
        }
        if (approved != null) {
            if (approved) {
                request.setStatus("approved");
                request.setApprovedBy(name);
                request.setApprovedDate(new Date());
            } else {
                request.setStatus("rejected");
                request.setRejectedBy(name);
                request.setRejectedDate(new Date());
            }
        }
        if (note != null) {
            request.setNote(note);
        }
        materialSupplyRepository.save(request);
        return "updated success";
    }
}
