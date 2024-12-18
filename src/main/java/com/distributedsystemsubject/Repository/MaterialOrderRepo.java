package com.distributedsystemsubject.Repository;

import com.distributedsystemsubject.Entity.MaterialOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialOrderRepo extends MongoRepository<MaterialOrder, String> {
    List<MaterialOrder> findAllByStatus(String status);
}
