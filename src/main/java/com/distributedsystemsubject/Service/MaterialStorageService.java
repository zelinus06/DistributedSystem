package com.distributedsystemsubject.Service;

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
    @Value("${spring.data.mongodb.supported-types}")
    private String supportedTypesConfig;
    private Set<String> SUPPORTED_TYPES;

    private void initializeSupportedTypes() {
        if (supportedTypesConfig != null && !supportedTypesConfig.isEmpty()) {
            String[] typesArray = supportedTypesConfig.split(",");
            SUPPORTED_TYPES = new HashSet<>();
            for (String type : typesArray) {
                SUPPORTED_TYPES.add(type.trim());
            }
        } else {
            SUPPORTED_TYPES = new HashSet<>();
        }
    }

    public void saveRequest(String jsonString) {
        try {
            initializeSupportedTypes();
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            String type = jsonNode.get("type").asText();
            if (!SUPPORTED_TYPES.contains(type)) {
                throw new IllegalArgumentException("Unsupported type: " + type);
            }
            if (type == null || type.isEmpty()) {
                throw new IllegalArgumentException("Missing 'type' field in JSON");
            }
            mongoTemplate.save(jsonString, type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save request: " + e.getMessage());
        }
    }
}
