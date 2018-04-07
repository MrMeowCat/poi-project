package org.mrmeowcat.poibackend.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories



@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = ["org.mrmeowcat.poibackend.domain"])
class MongoConfig {

    @Autowired lateinit var mongoDbFactory: MongoDbFactory

    @Autowired lateinit var mongoMappingContext: MongoMappingContext

    @Bean
    fun mappingMongoConverter(): MappingMongoConverter {
        val dbRefResolver = DefaultDbRefResolver(mongoDbFactory)
        val converter = MappingMongoConverter(dbRefResolver, mongoMappingContext)
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        return converter
    }
}