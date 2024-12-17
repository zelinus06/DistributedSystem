package com.distributedsystemsubject.Dto.Request;

import com.distributedsystemsubject.Entity.Materials;
import lombok.Data;

import java.util.List;

@Data
public class MaterialConsumeRequest {
    private List<Materials> materials;
}
