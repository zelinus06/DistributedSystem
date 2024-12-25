package com.distributedsystemsubject.Service.ManagerService;

import com.distributedsystemsubject.Entity.MaterialOrder;
import com.distributedsystemsubject.Repository.MaterialOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderRequestManagerService {
    @Autowired
    private MaterialOrderRepo materialOrderRepo;

    public List<MaterialOrder> getAllRequests() {
        return materialOrderRepo.findAll();
    }

    public void approveOrderRequest(String id, Boolean isApproved, String username) {
        Optional<MaterialOrder> materialOrder = materialOrderRepo.findById(id);
        if (materialOrder.isPresent()) {
            MaterialOrder order = materialOrder.get();
            if (isApproved) {
                order.setStatus("approved");
                order.setApprovedBy(username);
                String approvedDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                order.setApprovedDate(approvedDateString);
            } else {
                order.setStatus("Rejected");
            }
            materialOrderRepo.save(order);
        }

    }

}
