package com.distributedsystemsubject.Controller.AccountantController;

import com.distributedsystemsubject.Service.AccountantService.OrderRequestAccountantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accountant/order")
public class OrderRequestAccountantController {
    @Autowired
    private OrderRequestAccountantService orderRequestAccountantService;

    @GetMapping("/view")
    public ResponseEntity<?> getOrderRequest() {
        try {
           return ResponseEntity.ok(orderRequestAccountantService.getOrderRequest());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/paid")
    public ResponseEntity<?> paidOrderRequest(@RequestParam String id) {
        try {
            return ResponseEntity.ok(orderRequestAccountantService.paidOrderRequest(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
