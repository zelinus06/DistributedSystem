package com.distributedsystemsubject.Service.TeacherService;

import com.distributedsystemsubject.Entity.*;
import com.distributedsystemsubject.Repository.MaterialProvideRepo;
import com.distributedsystemsubject.Repository.TeacherWarhouseRepo;
import com.distributedsystemsubject.Repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ManageProvideRequestService {
    @Autowired
    private MaterialProvideRepo materialProvideRepository;
    @Autowired
    private WarehouseRepo warehouseRepo;
    @Autowired
    private TeacherWarhouseRepo teacherWarhouseRepo;

    public List<MaterialProvide> getAllRequest(String teacherName) {
        return materialProvideRepository.findAllByTeacherName(teacherName);
    }

    public void confirmRequestById(String requestId) {
        MaterialProvide materialProvide = materialProvideRepository.findByRequestId(requestId);
        if (materialProvide != null) {
            materialProvide.setStatus("confirmed");
            materialProvideRepository.save(materialProvide);
            updateQuantities(materialProvide);
        } else {
            throw new RuntimeException("Request not found");
        }
    }

    private void updateQuantities(MaterialProvide materialProvide) {
        // Lấy danh sách các vật tư trong yêu cầu
        List<Materials> requestedMaterials = materialProvide.getMaterials();

        // Cập nhật số lượng trong Warehouse
        Warehouse warehouse = warehouseRepo.findById(materialProvide.getSource())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        updateWarehouseMaterials(warehouse, requestedMaterials);

        // Cập nhật số lượng trong TeacherWarehouse
        TeacherWarhouse teacherWarehouse = teacherWarhouseRepo.findByTeacherName(materialProvide.getTeacherName())
                .orElseThrow(() -> new RuntimeException("TeacherWarehouse not found"));
        updateTeacherWarehouseMaterials(teacherWarehouse, requestedMaterials);
    }

    private void updateWarehouseMaterials(Warehouse warehouse, List<Materials> requestedMaterials) {
        for (Materials requestedMaterial : requestedMaterials) {
            warehouse.getMaterials().forEach(material -> {
                if (material.getName().equals(requestedMaterial.getName())) {
                    if (material.getQuantity() > requestedMaterial.getQuantity()) {
                        material.setQuantity(material.getQuantity() - requestedMaterial.getQuantity());
                    } else {
                        System.out.println("vler");
                        throw new RuntimeException("Not enough material in Warehouse for: " + material.getName());
                    }
                }
            });
        }
        warehouseRepo.save(warehouse);
    }

    private void updateTeacherWarehouseMaterials(TeacherWarhouse teacherWarehouse, List<Materials> requestedMaterials) {
        for (Materials requestedMaterial : requestedMaterials) {
            boolean materialExists = false;
            for (Materials material : teacherWarehouse.getMaterials()) {
                if (material.getName().equals(requestedMaterial.getName())) {
                    material.setQuantity(material.getQuantity() + requestedMaterial.getQuantity());
                    materialExists = true;
                    break;
                }
            }
            if (!materialExists) {
                teacherWarehouse.getMaterials().add(requestedMaterial);
            }
        }
        teacherWarhouseRepo.save(teacherWarehouse);
    }
}
