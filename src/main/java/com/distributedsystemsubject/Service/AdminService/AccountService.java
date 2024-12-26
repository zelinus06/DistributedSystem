package com.distributedsystemsubject.Service.AdminService;

import com.distributedsystemsubject.Dto.Response.AccountResponse;
import com.distributedsystemsubject.Entity.TeacherWarhouse;
import com.distributedsystemsubject.Entity.User;
import com.distributedsystemsubject.Enums.Role;
import com.distributedsystemsubject.Repository.TeacherWarhouseRepo;
import com.distributedsystemsubject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TeacherWarhouseRepo teacherWarehouseRepo;

    public AccountResponse createAccount(String username, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setRole(role);
        String randomPassword = generateRandomPassword(8);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(randomPassword);
        user.setPassword(encodedPassword);
        userRepo.save(user);

        if (role == Role.TEACHER) {
            TeacherWarhouse teacherWarehouse = new TeacherWarhouse();
            teacherWarehouse.setTeacherName(username);
            teacherWarehouseRepo.save(teacherWarehouse);
        }
        return new AccountResponse(user.getUsername(), randomPassword);
    }

    private String generateRandomPassword(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    public List<User> viewAccount() {
        return userRepo.findAll();
    }
}
