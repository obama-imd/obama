package br.ufrn.imd.obama.usuario.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val securityFilter: SecurityFilter
) {

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() }
            .headers { h -> h.frameOptions{ fo -> fo.disable()}}
            .sessionManagement{
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests{
                authorize -> authorize
                .requestMatchers(antMatcher(HttpMethod.POST, "/v1/auth/login")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET,"/v1/temaconteudo**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET,"/v1/descritor**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET,"/v1/oa**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET,"/v1/oa/**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET,"/v1/nivelensino**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET,"/v1/habilidade**")).permitAll()
                .requestMatchers(antMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(antMatcher( "/webjars/**")).permitAll()
                .requestMatchers(antMatcher("/swagger-ui.html")).permitAll()
                .requestMatchers(antMatcher("/swagger-resources/**")).permitAll()
                .requestMatchers(antMatcher( "/v3/api-docs/**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET, "/actuator/**")).permitAll()
                .requestMatchers(antMatcher( "/h2-console/**")).permitAll()
                .anyRequest().authenticated()
            }
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
