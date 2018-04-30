package org.mrmeowcat.poibackend.application.controller.v1

import org.apache.commons.lang3.RandomStringUtils
import org.mrmeowcat.poibackend.application.dto.UserDto
import org.mrmeowcat.poibackend.application.dto.request.SignUpRequest
import org.mrmeowcat.poibackend.config.SftpConfig
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *  User REST endpoints.
 */
@RestController
@RequestMapping(value = ["/api/v1"])
class UserController : AbstractController() {

    companion object {
        @JvmField val LOGGER = LoggerFactory.getLogger(UserController::class.java)
    }

    @GetMapping("currentUser")
    fun getCurrentUser(request: HttpServletRequest) : ResponseEntity<*> {
        val user = getCurrentUser()!!
        val userDto = mapper.map(user, UserDto::class)
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
    fun setLocale(@RequestParam("locale", required = true) locale: String = "en",
                  response: HttpServletResponse) : ResponseEntity<Any> {
        val user = getCurrentUser()

        if (user != null) {
            user.language = locale
            services.users.save(user)
            return ResponseEntity.status(HttpStatus.OK).build()
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @PutMapping("avatar")
    fun setAvatar(@RequestParam("file") part: MultipartFile): ResponseEntity<*> {
        val filename = "${RandomStringUtils.randomAlphabetic(4)}.png"
        services.sftpService.upload(part.inputStream, filename)
        val user = getCurrentUser()!!
        user.avatarFull = "${SftpConfig.PUBLIC_URL}/$filename"
        user.avatarThumbnail = "${SftpConfig.PUBLIC_URL}/$filename"
        user.avatarIcon = "${SftpConfig.PUBLIC_URL}/$filename"
        services.users.save(user)
        val response = mapOf(
                Pair("avatarFull", user.avatarFull),
                Pair("avatarThumbnail", user.avatarThumbnail),
                Pair("avatarIcon", user.avatarIcon))
        return ResponseEntity.ok(response)
    }
}