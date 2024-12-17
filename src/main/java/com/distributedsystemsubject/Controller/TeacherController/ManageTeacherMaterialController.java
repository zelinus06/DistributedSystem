package com.distributedsystemsubject.Controller.TeacherController;

import com.distributedsystemsubject.Dto.Request.MaterialConsumeRequest;
import com.distributedsystemsubject.Entity.Materials;
import com.distributedsystemsubject.Service.TeacherService.ManageTeacherWarhouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher/material")
public class ManageTeacherMaterialController {
    @Autowired
    private ManageTeacherWarhouseService manageTeacherWarhouseService;

    @GetMapping("/view")
    public ResponseEntity<?> viewMaterials() {
        try {
            return ResponseEntity.ok(manageTeacherWarhouseService.viewMaterials(getRequesterName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/consume")
    public ResponseEntity<?> minusMaterials(@RequestBody MaterialConsumeRequest materials) {
        try {
            manageTeacherWarhouseService.consumeMaterials(getRequesterName(), materials);
            return ResponseEntity.ok("Materials consumed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public String getRequesterName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
