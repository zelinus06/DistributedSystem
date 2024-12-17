package com.distributedsystemsubject.Repository;

import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Entity.MaterialSupply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialProvideRepo extends MongoRepository<MaterialProvide, String> {
    List<MaterialProvide> findAllByTeacherName(String teacherName);
    MaterialProvide findByRequestId(String requestId);
}
