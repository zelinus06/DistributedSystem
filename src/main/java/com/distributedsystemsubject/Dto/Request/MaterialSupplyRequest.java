package com.distributedsystemsubject.Dto.Request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MaterialSupplyRequest {
    private String requesterName;
    private String topicName;
    private String type;
    private List<Map<String, String>> materials;
    private List<String> classrooms;
    private String semester;
    private String purpose;
    private String date;
}
