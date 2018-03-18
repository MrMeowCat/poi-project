package org.mrmeowcat.poibackend.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = ["org.mrmeowcat.poibackend.domain.repository"])
class MongoConfig {
}