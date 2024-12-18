package com.distributedsystemsubject.Service.StoreKeeperService;

import com.distributedsystemsubject.Dto.Request.MaterialAddingRequest;
import com.distributedsystemsubject.Entity.Materials;
import com.distributedsystemsubject.Entity.Warehouse;
import com.distributedsystemsubject.Repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageWarhouseService {
    @Autowired
    private WarehouseRepo warehouseRepo;

    public void addMaterials(MaterialAddingRequest materialAddingRequest) {
        Warehouse warehouse = warehouseRepo.findById(materialAddingRequest.getId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        warehouse.getMaterials().addAll(materialAddingRequest.getMaterials());
        warehouseRepo.save(warehouse);
    }

    public List<Materials> viewMaterials(String id) {
        Warehouse warehouse = warehouseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return warehouse.getMaterials();
    }

    public Materials searchMaterialsByName(String materialName) {
        List<Warehouse> results = warehouseRepo.findByMaterialName(materialName);
        for (Warehouse warehouse : results) {
            for (Materials material : warehouse.getMaterials()) {
                if (material.getName().equalsIgnoreCase(materialName)) {
                    return material;
                }
            }
        }
        throw new RuntimeException("Material not found");
    }
}
