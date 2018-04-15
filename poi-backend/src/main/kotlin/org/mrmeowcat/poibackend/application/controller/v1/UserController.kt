package org.mrmeowcat.poibackend.application.controller.v1

import org.mrmeowcat.poibackend.application.dto.request.SignUpRequest
import org.mrmeowcat.poibackend.application.dto.UserDto
import org.mrmeowcat.poibackend.domain.document.User
import org.mrmeowcat.poibackend.domain.exception.DocumentNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = ["/api/v1"])
class UserController : AbstractController() {

    companion object {
        @JvmField val LOGGER = LoggerFactory.getLogger(UserController::class.java)
    }

    @GetMapping("currentUser")
    fun getCurrentUser(request: HttpServletRequest) : ResponseEntity<*> {
        val user = getCurrentUser()
        val userDto = mapper.map(user, UserDto::class)

        val linkBuilder = methodOn(UserController::class.java).getCurrentUser(request)
        userDto.add(linkTo(linkBuilder).withSelfRel())

        return ResponseEntity.ok(userDto)
    }

    @GetMapping("userExists")
    fun userExists(@RequestParam("username", required = true) username: String = "")
            : ResponseEntity<*> {
        return ResponseEntity.ok(services.users.existsByUsername(username))
    }

    @PostMapping("signUp")
    fun signUp(@RequestBody signUpRequest: SignUpRequest?) : ResponseEntity<*> {
        val response = services.signUpService.signUp(signUpRequest)
        return ResponseEntity.ok(response)
    }

    @PutMapping("setLocale")
    fun setLocale(
            @RequestParam("locale", required = true) locale: String = "en",
            response: HttpServletResponse) {
        val cookie = Cookie("locale", locale)
        cookie.domain = "localhost"
        cookie.path = "/"
        cookie.maxAge = 100
        response.addCookie(cookie)

        try {
            val user = getCurrentUser()
            user.language = locale
            services.users.save(user)
        } catch (e: DocumentNotFoundException) {
            LOGGER.info("Setting locale $locale for anonymous user")
        }
    }

    private fun getCurrentUser() : User {
        val username = SecurityContextHolder.getContext().authentication.name
        return services.users.findByUsername(username)
    }
}