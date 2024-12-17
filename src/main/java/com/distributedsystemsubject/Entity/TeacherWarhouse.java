package com.distributedsystemsubject.Entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "TeacherWarehouse")
@Getter
@Setter
public class TeacherWarhouse {
    @Id
    private String id;
    private String teacherName;
    private List<Materials> materials;
}
