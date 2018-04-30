package org.mrmeowcat.poibackend.domain.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Gateway for all services.
 */
@Component
class Services @Autowired
constructor(val users: UserService,
            val signUpService: SignUpService,
            val sftpService: FileTransferService,
            val themes: ThemeService)