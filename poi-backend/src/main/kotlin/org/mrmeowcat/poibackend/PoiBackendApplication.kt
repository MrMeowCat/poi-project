package org.mrmeowcat.poibackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class PoiBackendApplication

fun main(args: Array<String>) {
    runApplication<PoiBackendApplication>(*args)
}
