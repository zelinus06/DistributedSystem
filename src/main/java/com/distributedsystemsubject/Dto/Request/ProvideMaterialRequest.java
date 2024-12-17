package com.distributedsystemsubject.Dto.Request;

import com.distributedsystemsubject.Entity.Materials;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ProvideMaterialRequest {
    private String requestId;
    private String topicName;// No need to fill in fe
    private String storekeeperName;// No need to fill in fe
    private String type;// No need to fill in fe
    private String teacherName;
    private List<String> classrooms;
    private String semester;
    private List<Materials> materials;
    private String purpose;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date date;// No need to fill in fe
    private String source;
}
