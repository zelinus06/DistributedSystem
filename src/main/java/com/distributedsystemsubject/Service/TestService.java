//package com.distributedsystemsubject.Service;
//
//import com.distributedsystemsubject.Entity.Materials;
//import com.distributedsystemsubject.Entity.TeacherWarhouse;
//import com.distributedsystemsubject.Entity.User;
//import com.distributedsystemsubject.Entity.Warehouse;
//import com.distributedsystemsubject.Enums.Role;
//import com.distributedsystemsubject.Repository.MaterialsRepo;
//import com.distributedsystemsubject.Repository.TeacherWarhouseRepo;
//import com.distributedsystemsubject.Repository.UserRepo;
//import com.distributedsystemsubject.Repository.WarehouseRepo;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TestService {
//    @Autowired
//    private TeacherWarhouseRepo teacherWarhouseRepository;
//
//    @PostConstruct
//    public void init() {
//        // Tạo các đối tượng Materials
//        // Tạo các đối tượng Materials
//        Materials material1 = new Materials();
//        material1.setName("Laptop Charger");
//        material1.setQuantity(5);
//        material1.setUnit("pieces");
//
//        Materials material2 = new Materials();
//        material2.setName("Projector Bulb");
//        material2.setQuantity(2);
//        material2.setUnit("pieces");
//
//        Materials material3 = new Materials();
//        material3.setName("Whiteboard Marke");
//        material3.setQuantity(10);
//        material3.setUnit("pieces");
//
//        // Tạo một TeacherWarhouse và gán Materials
//        TeacherWarhouse teacherWarhouse = new TeacherWarhouse();
//        teacherWarhouse.setTeacherName("John Doe");
//        teacherWarhouse.setMaterials(List.of(material1, material2, material3));
//
//        // Lưu TeacherWarhouse vào MongoDB
//        teacherWarhouseRepository.save(teacherWarhouse);
//    }
//}
