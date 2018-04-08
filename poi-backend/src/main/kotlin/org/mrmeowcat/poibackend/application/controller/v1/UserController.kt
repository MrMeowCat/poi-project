package org.mrmeowcat.poibackend.application.controller.v1

import org.mrmeowcat.poibackend.application.dto.SignUpRequest
import org.mrmeowcat.poibackend.application.dto.UserDto
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["/api/v1"])
class UserController : AbstractController() {

    @GetMapping("currentUser")
    fun getCurrentUser(request: HttpServletRequest) : ResponseEntity<*> {
        val username = getCurrentUser()
        val user = services.users.findByUsername(username)
        val userDto = mapper.map(user, UserDto::class)

        val linkBuilder = methodOn(UserController::class.java).getCurrentUser(request)
        userDto.add(linkTo(linkBuilder).withSelfRel())

        return ResponseEntity.ok(userDto)
    }

    @PostMapping("signUp")
    fun signUp(@RequestBody signUpRequest: SignUpRequest?) : ResponseEntity<*> {
        services.signUpService.signUp(signUpRequest)
        return ResponseEntity.ok("")
    }

    private fun getCurrentUser() : String {
        return SecurityContextHolder.getContext().authentication.name
    }
}