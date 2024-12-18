package com.distributedsystemsubject.Dto.Request;

import com.distributedsystemsubject.Entity.ListMaterialOrder;
import com.distributedsystemsubject.Entity.Materials;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderMaterialRequest {
    private String requesterName;
    private String topicName;
    private String type;
    private List<ListMaterialOrder> materials;
    private String purpose;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date date;
}
