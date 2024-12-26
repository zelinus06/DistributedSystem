package com.distributedsystemsubject.Controller.StoreKeeperController;

import com.distributedsystemsubject.Dto.Request.MaterialAddingRequest;
import com.distributedsystemsubject.Entity.Materials;
import com.distributedsystemsubject.Entity.Warehouse;
import com.distributedsystemsubject.Service.StoreKeeperService.ManageWarhouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        materialAddingRequest.setId("67613d7bd6d5e23a7d90a609");
        materialAddingRequest.setName("khovattu");
        manageWarhouseService.addMaterials(materialAddingRequest);
        return ResponseEntity.ok("Material added");
    }
    @GetMapping("/view")
    public ResponseEntity<List<Materials>> viewMaterial() {
        List<Materials> list = manageWarhouseService.viewMaterials("67613d7bd6d5e23a7d90a609");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMaterials(@RequestParam String materialName) {
        try {
            Materials results = manageWarhouseService.searchMaterialsByName(materialName);
            System.out.println("kết quả là: " + results);
            return ResponseEntity.ok(results);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material not found: " + materialName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
