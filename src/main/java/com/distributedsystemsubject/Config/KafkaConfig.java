package com.distributedsystemsubject.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class KafkaConfig {
    @Value("${kafka.topics}")
    private List<String> topicNames;

    @Bean
    public KafkaAdmin.NewTopics createTopics() {
        List<NewTopic> topics = new ArrayList<>();
        for (String name : topicNames) {
            topics.add(TopicBuilder.name(name)
                    .partitions(3)
                    .replicas(1)
                    .build());
        }
        return new KafkaAdmin.NewTopics(topics.toArray(new NewTopic[0]));
    }
}
