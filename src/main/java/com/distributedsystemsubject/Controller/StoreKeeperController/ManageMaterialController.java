package com.distributedsystemsubject.Controller.StoreKeeperController;

import com.distributedsystemsubject.Dto.Request.MaterialAddingRequest;
import com.distributedsystemsubject.Entity.Materials;
import com.distributedsystemsubject.Service.StoreKeeperService.ManageWarhouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/storekeeper/material")
public class ManageMaterialController {
    @Autowired
    private ManageWarhouseService manageWarhouseService;

    @PostMapping("/add")
    public ResponseEntity<String> addMaterial(@RequestBody MaterialAddingRequest materialAddingRequest) {
        manageWarhouseService.addMaterials(materialAddingRequest);
        return ResponseEntity.ok("Material added");
    }
    @GetMapping("/view")
    public ResponseEntity<List<Materials>> viewMaterial() {
        List<Materials> list = manageWarhouseService.viewMaterials("67613d7bd6d5e23a7d90a609");
        return ResponseEntity.ok(list);
    }
}
