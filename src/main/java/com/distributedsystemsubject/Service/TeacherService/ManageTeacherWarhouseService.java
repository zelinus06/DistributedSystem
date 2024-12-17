package com.distributedsystemsubject.Service.TeacherService;

import com.distributedsystemsubject.Dto.Request.MaterialConsumeRequest;
import com.distributedsystemsubject.Entity.Materials;
import com.distributedsystemsubject.Entity.TeacherWarhouse;
import com.distributedsystemsubject.Entity.Warehouse;
import com.distributedsystemsubject.Repository.TeacherWarhouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManageTeacherWarhouseService {
    @Autowired
    private TeacherWarhouseRepo teacherWarhouseRepo;

    public List<Materials> viewMaterials(String teacherName) {
        Optional<TeacherWarhouse> warehouse = teacherWarhouseRepo.findByTeacherName(teacherName);
        if (warehouse.isPresent()) {
            return warehouse.get().getMaterials();
        } else {
            throw new RuntimeException("TeacherWarehouse not found");
        }
    }

    public void consumeMaterials(String teacherName, MaterialConsumeRequest materials) {
        TeacherWarhouse teacherWarhouse = teacherWarhouseRepo.findByTeacherName(teacherName)
                .orElseThrow(() -> new RuntimeException("TeacherWarehouse not found"));

        List<Materials> teacherMaterials = teacherWarhouse.getMaterials();

        for (Materials material : materials.getMaterials()) {
            Materials teacherMaterial = teacherMaterials.stream()
                    .filter(tm -> tm.getName().equals(material.getName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Material not found: " + material.getName()));
            if (teacherMaterial.getQuantity() >= material.getQuantity()) {
                teacherMaterial.setQuantity(teacherMaterial.getQuantity() - material.getQuantity());
            } else {
                throw new RuntimeException("Not enough materials for: " + material.getName());
            }
        }
        teacherWarhouseRepo.save(teacherWarhouse);
    }
}
