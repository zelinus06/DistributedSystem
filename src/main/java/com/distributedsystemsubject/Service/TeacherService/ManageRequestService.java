package com.distributedsystemsubject.Service.TeacherService;

import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Repository.MaterialSupplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ManageRequestService {
    @Autowired
    private MaterialSupplyRepo materialSupplyRepository;

    public Page<MaterialSupply> getAllRequests(String requesterName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return materialSupplyRepository.findAllByRequesterName(requesterName, pageable);
    }

    public MaterialSupply getRequestById(String requesterName, String id) {
        if (materialSupplyRepository.existsByIdAndRequesterName(id, requesterName)) {
            return materialSupplyRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Material supply request not found for the given ID."));
        } else {
            throw new RuntimeException("Material supply request not found for the given requester name and ID.");
        }
    }

    public void updateMaterialRequest(String requesterName, MaterialSupply updatedRequest) {
        if (materialSupplyRepository.existsByIdAndRequesterName(updatedRequest.getId(), requesterName) && updatedRequest.getStatus().equals("pending")) {
            updatedRequest.setId(updatedRequest.getId());
            materialSupplyRepository.save(updatedRequest);
        } else {
            throw new RuntimeException("Request not found");
        }
    }
}
