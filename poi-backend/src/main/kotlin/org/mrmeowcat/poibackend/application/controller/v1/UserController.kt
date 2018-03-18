package org.mrmeowcat.poibackend.application.controller.v1

import org.mrmeowcat.poibackend.application.dto.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["/api/v1"])
class UserController : AbstractController() {

    @GetMapping("currentUser")
    fun getCurrentUser(request: HttpServletRequest) : ResponseEntity<*> {
        val username = getCurrentUser()
        val user = services.users.findByName(username)
        val userDto = mapper.map(user, UserDto::class)
        return ResponseEntity.ok(userDto)
    }

    private fun getCurrentUser() : String {
        return SecurityContextHolder.getContext().authentication.name
    }
}