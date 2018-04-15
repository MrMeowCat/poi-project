package org.mrmeowcat.poibackend.config

import org.mrmeowcat.poibackend.application.security.filter.LoginFilter
import org.mrmeowcat.poibackend.application.security.filter.TokenFilter
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
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

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
                .antMatchers(HttpMethod.PUT, "/api/v1/setLocale").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/currentUser").hasAuthority("USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(TokenFilter(authenticationManager()))
                .addFilter(LoginFilter("/api/v1/login", authenticationManager()))

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