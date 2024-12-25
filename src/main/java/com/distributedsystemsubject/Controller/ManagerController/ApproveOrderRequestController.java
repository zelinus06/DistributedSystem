package com.distributedsystemsubject.Controller.ManagerController;

import com.distributedsystemsubject.Service.ManagerService.OrderRequestManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/manager/order")
public class ApproveOrderRequestController {
    @Autowired
    private OrderRequestManagerService orderRequestManagerService;

    @GetMapping("/view")
    public ResponseEntity<?> getApproveOrderRequest() {
        return ResponseEntity.ok(orderRequestManagerService.getAllRequests());
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approveOrderRequest(@RequestParam String id, @RequestParam Boolean isApproved) {
        try {
            orderRequestManagerService.approveOrderRequest(id, isApproved, getRequesterName());
            return ResponseEntity.ok(Map.of("success", "Đã xác nhận"));
        } catch (RuntimeException e) {
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
