package com.kardex.infrastructure.config;

import com.kardex.infrastructure.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // 🔹 Permitir H2 Console en iframes
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Permitir Swagger UI sin autenticación
                    http.requestMatchers(HttpMethod.GET, "/api-docs/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "doc/swagger-ui/**").permitAll();
                    http.requestMatchers(HttpMethod.GET, "doc/swagger-ui.html").permitAll();

                    // Permitir acceso a H2 Console sin autenticación
                    http.requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/h2-console/**").permitAll();

                    // Proteger todas las demás rutas
                    http.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
