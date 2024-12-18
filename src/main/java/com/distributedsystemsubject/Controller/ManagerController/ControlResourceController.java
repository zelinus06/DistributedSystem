package com.distributedsystemsubject.Controller.ManagerController;

import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Entity.MaterialSupply;
import com.distributedsystemsubject.Service.ManagerService.OtherRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/manager/control-resource")
public class ControlResourceController {
    @Autowired
    private OtherRequestService otherRequestService;

    @GetMapping("/supply-requests")
    public ResponseEntity<?>getAllSupplyRequests() {
        return ResponseEntity.ok(otherRequestService.getAllSupplyRequests());
    }

    @GetMapping("/provide-requests")
    public ResponseEntity<?>getAllProvideRequests() {
        return ResponseEntity.ok(otherRequestService.getAllProvideRequests());
    }

    @GetMapping("/materials")
    public ResponseEntity<?>getAllMaterials() {
        return ResponseEntity.ok(otherRequestService.viewMaterials());
    }
}
