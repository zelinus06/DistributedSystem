package com.distributedsystemsubject.Repository;

import com.distributedsystemsubject.Entity.Materials;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialsRepo extends MongoRepository<Materials, String> {
}
