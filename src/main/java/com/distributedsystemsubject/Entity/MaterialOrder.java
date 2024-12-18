package com.distributedsystemsubject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "MaterialOrder")
@Getter
@Setter
public class MaterialOrder {
    @Id
    private String id;
    private String requesterName;
    private String topicName;
    private String type;
    private List<ListMaterialOrder> materials;
    private String purpose;
    private String date;
    private String status;
    private String approvedBy;
    private String approvedDate;
}
