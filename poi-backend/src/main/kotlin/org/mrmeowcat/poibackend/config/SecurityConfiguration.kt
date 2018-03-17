package org.mrmeowcat.poibackend.config

import org.mrmeowcat.poibackend.application.security.filter.JwtAuthenticationFilter
import org.mrmeowcat.poibackend.application.security.filter.JwtProcessingFilter
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

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var securityUserDetailsService: SecurityUserDetailsService

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(JwtProcessingFilter(authenticationManager()))
                .addFilter(JwtAuthenticationFilter("/api/v1/login", authenticationManager()))

    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    override fun authenticationManager() = super.authenticationManager()

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(securityUserDetailsService).passwordEncoder(passwordEncoder())
    }
}