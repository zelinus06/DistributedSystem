package com.distributedsystemsubject.Entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "MaterialSupply")
@Getter
@Setter
public class MaterialSupply {
    @Id
    private String id;
    private String requesterName;
    private String topicName;
    private String type;
    private List<Map<String, String>> materials;
    private List<String> classrooms;
    private String semester;
    private String purpose;
    private String date;
    private String note;
    private String status;
    private String approvedBy;
    private Date approvedDate;
    private String rejectedBy;
    private Date rejectedDate;
}
