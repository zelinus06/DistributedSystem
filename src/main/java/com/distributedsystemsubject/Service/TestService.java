//package com.distributedsystemsubject.Service;
//
//import com.distributedsystemsubject.Entity.User;
//import com.distributedsystemsubject.Enums.Role;
//import com.distributedsystemsubject.Repository.UserRepo;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TestService {
//    @Autowired
//    private UserRepo userRepo;
//
//    @PostConstruct
//    public void init() {
//        User user = new User();
//        user.setUsername("thai");
//        user.setPassword("hello");
//        user.setRole(Role.USER); // hoặc vai trò bạn muốn
//        userRepo.save(user); // Lưu user vào MongoDB
//    }
//}
