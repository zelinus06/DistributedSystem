package com.distributedsystemsubject.Controller.StoreKeeperController;

import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Service.StoreKeeperService.ManageMaterialRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/storekeeper/request")
public class ManageMaterialRequestController {
    @Autowired
    private ManageMaterialRequestService manageMaterialRequestService;

    @GetMapping("/view")
    public Page<MaterialSupply> getMaterialSupplyRequests(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return manageMaterialRequestService.getAllRequests(page, size);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<MaterialSupply> getMaterialSupplyRequestById(@PathVariable String id) {
        MaterialSupply request = manageMaterialRequestService.getRequestById(id);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(request);
    }

    @PostMapping("/view/{id}/approve")
    public ResponseEntity<String> approveMaterialSupplyRequest(@PathVariable String id, @RequestParam Boolean approved, @RequestParam(required = false) String note) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String result = manageMaterialRequestService.approveRequest(id, approved, note, username);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid request data");
        }
    }

    @PostMapping("/view/{id}/update")
    public ResponseEntity<String> updateMaterialSupplyRequest(@RequestBody MaterialSupply updatedRequest) {
        try {
            manageMaterialRequestService.updateMaterialRequest(updatedRequest);
            return ResponseEntity.ok("updatedRequest");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing material supply request: " + e.getMessage());
        }
    }
}
