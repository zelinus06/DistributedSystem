package com.distributedsystemsubject.Dto.Request;

import com.distributedsystemsubject.Entity.Materials;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class MaterialSupplyRequest {
    private String requesterName;
    private String topicName;
    private String type;
    private List<Materials> materials;
    private List<String> classrooms;
    private String semester;
    private String purpose;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date date;
}
