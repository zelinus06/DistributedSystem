package com.distributedsystemsubject.Dto.Request;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ProvideMaterialRequest {
    private String id;
    private String topicName;
    private String storekeeperName;
    private String teacherName;
    private List<String> classrooms;
    private String semester;
    private String type;
    private List<Map<String, String>> materials;
    private String purpose;
    private Date date;
}
