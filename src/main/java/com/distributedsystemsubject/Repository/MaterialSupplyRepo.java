package com.distributedsystemsubject.Repository;

import com.distributedsystemsubject.Entity.MaterialSupply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialSupplyRepo extends MongoRepository<MaterialSupply, String> {
    Page<MaterialSupply> findAll(Pageable pageable);
}
