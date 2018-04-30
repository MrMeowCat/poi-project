package org.mrmeowcat.poibackend.domain.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Gateway for all repositories.
 */
@Component
class Repositories @Autowired
constructor(val users: MongoUserRepository,
            val versions: MongoVersionRepository,
            val themes: MongoThemeRepository)