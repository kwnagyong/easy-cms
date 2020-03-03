package com.pky.easycms.configuration

import com.pky.easycms.configuration.security.filter.FilterSkipMatcher
import com.pky.easycms.configuration.security.filter.JwtAuthenticationFilter
import com.pky.easycms.configuration.security.provider.JwtAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(private val jwtAuthenticationProvider: JwtAuthenticationProvider) : WebSecurityConfigurerAdapter() {
    private val whitePatterns = arrayOf("/auth/sign-up", "/auth/sign-in")

    override fun configure(http: HttpSecurity) {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, *whitePatterns).permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and().headers().frameOptions().sameOrigin()

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(jwtAuthenticationProvider)
    }

    fun jwtAuthenticationFilter(): JwtAuthenticationFilter {
        val result = JwtAuthenticationFilter(FilterSkipMatcher(whitePatterns))
        result.setAuthenticationManager(super.authenticationManagerBean())
        return result
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}