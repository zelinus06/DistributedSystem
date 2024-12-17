package com.distributedsystemsubject.Service.StoreKeeperService;

import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Repository.MaterialProvideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvideRequestService {
    @Autowired
    private MaterialProvideRepo materialProvideRepo;

    public List<MaterialProvide> getAllRequests() {
        return materialProvideRepo.findAll();
    }
}
