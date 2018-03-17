package org.mrmeowcat.poibackend.domain.service.impl

import org.mrmeowcat.poibackend.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Services @Autowired
constructor(val users: UserService)