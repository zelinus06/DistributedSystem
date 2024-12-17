package com.distributedsystemsubject.Repository;

import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Entity.TeacherWarhouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherWarhouseRepo extends MongoRepository<TeacherWarhouse, String> {
    Optional<TeacherWarhouse> findByTeacherName(String teacherName);
}
