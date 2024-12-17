package com.distributedsystemsubject.Entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "Warehouse")
@Getter
@Setter
public class Warehouse {
    @Id
    private String id;
    private String name;
    private List<Materials> materials;
}
