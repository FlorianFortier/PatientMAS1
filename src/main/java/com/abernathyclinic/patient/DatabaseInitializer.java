package com.abernathyclinic.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

    @Bean
    CommandLineRunner init(MongoTemplate mongoTemplate) {
        return args -> {
            if (!mongoTemplate.collectionExists("exampleCollection")) {
                mongoTemplate.createCollection("exampleCollection");
                mongoTemplate.insert(new ExampleDocument("example"), "exampleCollection");
            }
        };
    }

    static class ExampleDocument {
        private String name;

        public ExampleDocument(String name) {
            this.name = name;
        }

        // getters and setters
    }
}
