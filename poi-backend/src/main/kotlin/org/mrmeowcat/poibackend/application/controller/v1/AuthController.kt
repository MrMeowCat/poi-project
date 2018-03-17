package org.mrmeowcat.poibackend.application.controller.v1

import org.mrmeowcat.poibackend.application.security.JwtUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["/api/v1"],
        method = [RequestMethod.POST, RequestMethod.GET],
        consumes = ["application/json"],
        produces = ["application/json"])
class AuthController
constructor() : AbstractController() {

    @GetMapping("currentUser")
    fun getCurrentUser(request: HttpServletRequest) {
        val token = request.getHeader(JwtUtils.JWT_HEADER)

    }
}