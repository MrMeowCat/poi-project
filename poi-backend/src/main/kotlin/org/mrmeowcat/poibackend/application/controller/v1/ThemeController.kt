package org.mrmeowcat.poibackend.application.controller.v1

import org.mrmeowcat.poibackend.application.dto.ThemeDto
import org.mrmeowcat.poibackend.domain.document.Theme
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Theme REST endpoints.
 */
@RestController
@RequestMapping(value = ["/api/v1"])
class ThemeController : AbstractController() {

    companion object {
        @JvmField val LOGGER = LoggerFactory.getLogger(ThemeController::class.java)
    }

    @GetMapping("currentTheme")
    fun getCurrentTheme(): ResponseEntity<ThemeDto> {
        val user = getCurrentUser()!!
        val theme = services.themes.findById(user.themeId!!)
        val themeDto = mapper.map(theme, ThemeDto::class)
        return ResponseEntity.ok(themeDto)
    }

    @GetMapping("themes")
    fun getThemes(): ResponseEntity<Any> {
        val user = getCurrentUser()!!
        val themes = services.themes.findByUserId(user.id!!)
        val themeDtos = themes.map { mapper.map(it, ThemeDto::class) }
        return ResponseEntity.ok(themeDtos)
    }

    @PostMapping("themes")
    fun createTheme(@RequestBody themeDto: ThemeDto?): ResponseEntity<ThemeDto> {
        themeDto ?: return ResponseEntity.badRequest().build()
        val user = getCurrentUser()!!
        val theme = Theme()
        theme.name = themeDto.name
        theme.style = themeDto.style
        theme.userId = user.id
        services.themes.save(theme)
        services.users.save(user)
        themeDto.id = theme.id
        return ResponseEntity.status(HttpStatus.CREATED).body(themeDto)
    }

    @PutMapping("themes")
    fun updateTheme(@RequestBody themeDto: ThemeDto?): ResponseEntity<Any> {
        themeDto ?: return ResponseEntity.badRequest().build()
        val theme = services.themes.findById(themeDto.id!!)
        theme.name = themeDto.name
        theme.style = themeDto.style
        services.themes.save(theme)

        /* save current user theme if matches */
        val user = getCurrentUser()!!
        if (theme.userId == user.id) {
            user.themeId = theme.id
            services.users.save(user)
        }

        return ResponseEntity.ok().build()
    }
}
