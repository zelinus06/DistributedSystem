package com.distributedsystemsubject.Dto.Request;

import com.distributedsystemsubject.Entity.Materials;
import lombok.Data;

import java.util.List;

@Data
public class MaterialAddingRequest {
    private String id;
    private String name;
    private List<Materials> materials;
}
