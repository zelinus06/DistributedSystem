package com.distributedsystemsubject.Service.ManagerService;

import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Entity.Materials;
import com.distributedsystemsubject.Entity.Warehouse;
import com.distributedsystemsubject.Repository.MaterialProvideRepo;
import com.distributedsystemsubject.Repository.MaterialSupplyRepo;
import com.distributedsystemsubject.Repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherRequestService {
    @Autowired
    private MaterialSupplyRepo materialSupplyRepo;
    @Autowired
    private MaterialProvideRepo materialProvideRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;

    public List<MaterialSupply> getAllSupplyRequests() {
        return materialSupplyRepo.findAll();
    }

    public List<MaterialProvide> getAllProvideRequests() {
        return materialProvideRepo.findAll();
    }

    public List<Materials> viewMaterials() {
        Warehouse warehouse = warehouseRepo.findById("67613d7bd6d5e23a7d90a609")
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return warehouse.getMaterials();
    }
}
