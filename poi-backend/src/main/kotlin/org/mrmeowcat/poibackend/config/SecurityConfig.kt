package org.mrmeowcat.poibackend.config

import org.mrmeowcat.poibackend.application.security.filter.LoginFilter
import org.mrmeowcat.poibackend.application.security.filter.TokenFilter
import org.mrmeowcat.poibackend.application.security.handler.LogoutHandler
import org.mrmeowcat.poibackend.application.security.service.SecurityUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*


/**
 * Security configuration.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    companion object {
        private val bundle = ResourceBundle.getBundle("security")
        val COOKIE_DOMAIN = bundle.getString("auth.cookie.domain")
        val COOKIE_SECURE = bundle.getString("auth.cookie.secure")!!.toBoolean()
        val AUTH_COOKIE_AGE = bundle.getString("auth.cookie.age").toInt()
        val REMEMBER_ME_COOKIE_NAME = bundle.getString("auth.cookie.rememberMe.name")
        val CSRF_COOKIE_NAME = bundle.getString("auth.cookie.csrf.name")
        val CSRF_TOKEN_LENGTH = bundle.getString("auth.token.csrf.length").toInt()
        val CSRF_TOKEN_HEADER = bundle.getString("auth.token.csrf.header")
        val JWT_AGE = bundle.getString("auth.token.jwt.age").toInt()
        val JWT_SECRET = bundle.getString("auth.token.jwt.secret")
    }

    @Autowired
    lateinit var securityUserDetailsService: SecurityUserDetailsService

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/signUp").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/userExists").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/isAuthenticated").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/setLocale").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(TokenFilter(authenticationManager()))
                .addFilter(LoginFilter("/api/v1/login", authenticationManager()))
                .logout().logoutRequestMatcher(AntPathRequestMatcher("/api/v1/logout", HttpMethod.POST.name))
                .logoutSuccessHandler(LogoutHandler())

    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    override fun authenticationManager() = super.authenticationManager()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val configuration = CorsConfiguration()
        configuration.applyPermitDefaultValues()
        configuration.allowCredentials = true
        configuration.allowedMethods = mutableListOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}