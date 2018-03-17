package org.mrmeowcat.poibackend.domain.service

import org.mrmeowcat.poibackend.domain.repository.Repositories
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
abstract class AbstractDBService {

    @Autowired protected lateinit var repositories: Repositories
}