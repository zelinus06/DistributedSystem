package com.distributedsystemsubject.Service.StoreKeeperService;

import com.distributedsystemsubject.Dto.Request.MaterialAddingRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MaterialStorageService {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveRequest(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String type = jsonNode.get("type").asText();
            mongoTemplate.save(jsonString, type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save request: " + e.getMessage());
        }
    }
}
