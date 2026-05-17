package com.grupo6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitado para facilitar pruebas con APIs REST
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas (Swagger, Scalar y documentación)
                .requestMatchers(
                    "/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/scalar/**",
                    "/index.html",
                    "/"
                ).permitAll()
                // El usuario normal solo puede ver (GET) las rutas expuestas
                .requestMatchers(HttpMethod.GET, "/estudiantes/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/profesores/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/materias/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyRole("ADMIN", "USER")
                // Solo el administrador puede crear, editar o borrar
                .requestMatchers(HttpMethod.POST, "/estudiantes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/profesores/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/materias/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/usuarios/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
                
                .anyRequest().authenticated()
            )
            .cors(Customizer.withDefaults()) // Habilita CORS
            .httpBasic(Customizer.withDefaults()); // Habilita Autenticación Básica

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Permitir todos los orígenes
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
