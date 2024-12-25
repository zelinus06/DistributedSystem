package com.distributedsystemsubject.Service.AccountantService;

import com.distributedsystemsubject.Entity.MaterialOrder;
import com.distributedsystemsubject.Repository.MaterialOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRequestAccountantService {
    @Autowired
    private MaterialOrderRepo materialOrderRepo;

    public List<MaterialOrder> getOrderRequest() {
       List<MaterialOrder> list = materialOrderRepo.findAllByStatus("approved");
       if (!list.isEmpty()) {
           return list;
       } else {
           throw new RuntimeException("No order request found");
       }
    }

    public Object paidOrderRequest(String id) {
        MaterialOrder materialOrder = materialOrderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order request not found"));
        if (materialOrder.getStatus().equals("Approved")) {
            materialOrder.setStatus("Paid");
            materialOrderRepo.save(materialOrder);
            return "Order request paid";
        } else {
            throw new RuntimeException("Order request not approved yet");
        }
    }
}
