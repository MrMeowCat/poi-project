package org.mrmeowcat.poibackend.application.controller.v1

import org.mrmeowcat.poibackend.application.converter.Converters
import org.mrmeowcat.poibackend.domain.repository.Repositories
import org.mrmeowcat.poibackend.domain.service.impl.Services
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
abstract class AbstractController {
    @Autowired protected lateinit var repositories: Repositories
    @Autowired protected lateinit var services: Services
    @Autowired protected lateinit var converters: Converters
}