package org.mrmeowcat.poibackend.domain.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Repositories @Autowired
constructor(val users: UserRepository,
            val versions: VersionRepository)