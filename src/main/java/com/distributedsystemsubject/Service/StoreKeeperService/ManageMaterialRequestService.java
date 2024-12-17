package com.distributedsystemsubject.Service.StoreKeeperService;

import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Repository.MaterialSupplyRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ManageMaterialRequestService {
    @Autowired
    private MaterialSupplyRepo materialSupplyRepository;

    public Page<MaterialSupply> getAllRequests(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return materialSupplyRepository.findAll(pageable);
    }

    public MaterialSupply getRequestById(String id) {
        return materialSupplyRepository.findById(id).orElse(null);
    }

    public String approveRequest(String id, Boolean approved, String note, String username) throws JsonProcessingException {
        MaterialSupply request = materialSupplyRepository.findById(id).orElse(null);
        if (request == null) {
            throw new RuntimeException("Request not found");
        }
        if (approved != null) {
            if (approved) {
                request.setStatus("approved");
                request.setApprovedBy(username);
                String approvedDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                request.setApprovedDate(approvedDateString);
            } else {
                request.setStatus("rejected");
                request.setRejectedBy(username);
                String rejectedDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                request.setRejectedDate(rejectedDateString);
            }
        }
        if (note != null) {
            request.setNote(note);
        }
        materialSupplyRepository.save(request);
        return "updated success";
    }

    public void updateMaterialRequest(MaterialSupply updatedRequest) {
        if (materialSupplyRepository.existsById(updatedRequest.getId())) {
            updatedRequest.setId(updatedRequest.getId());
            materialSupplyRepository.save(updatedRequest);
        } else {
            throw new RuntimeException("Request not found");
        }
    }

}
