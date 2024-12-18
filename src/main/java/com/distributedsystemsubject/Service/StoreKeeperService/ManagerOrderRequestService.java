package com.distributedsystemsubject.Service.StoreKeeperService;

import com.distributedsystemsubject.Entity.MaterialOrder;
import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Repository.MaterialOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerOrderRequestService {
    @Autowired
    private MaterialOrderRepo materialOrderRepo;

    public List<MaterialOrder> getAllRequests() {
        return materialOrderRepo.findAll();
    }
}
