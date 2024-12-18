package com.distributedsystemsubject.Repository;

import com.distributedsystemsubject.Entity.MaterialProvide;
import com.distributedsystemsubject.Entity.Warehouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepo extends MongoRepository<Warehouse, String>  {
    // Tìm tất cả Warehouse chứa vật tư có tên trùng khớp
    @Query("{ 'materials.name': { $regex: ?0, $options: 'i' } }")
    List<Warehouse> findByMaterialName(String materialName);
}
