package com.grupo6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                // Deshabilitar CSRF
                .csrf(csrf -> csrf.disable())

                // Configuración de permisos
                .authorizeHttpRequests(auth -> auth

                        // Swagger / Scalar públicos
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/scalar/**"
                        ).permitAll()

                        // =========================
                        // GET -> ADMIN y USER
                        // =========================
                        .requestMatchers(HttpMethod.GET,
                                "/usuarios",
                                "/usuarios/**",
                                "/estudiantes",
                                "/estudiantes/**",
                                "/profesores",
                                "/profesores/**",
                                "/materias",
                                "/materias/**"
                        ).hasAnyRole("ADMIN", "USER")

                        // =========================
                        // POST -> SOLO ADMIN
                        // =========================
                        .requestMatchers(HttpMethod.POST,
                                "/usuarios",
                                "/usuarios/**",
                                "/estudiantes",
                                "/estudiantes/**",
                                "/profesores",
                                "/profesores/**",
                                "/materias",
                                "/materias/**"
                        ).hasRole("ADMIN")

                        // =========================
                        // PUT -> SOLO ADMIN
                        // =========================
                        .requestMatchers(HttpMethod.PUT, "/**")
                        .hasRole("ADMIN")

                        // =========================
                        // DELETE -> SOLO ADMIN
                        // =========================
                        .requestMatchers(HttpMethod.DELETE, "/**")
                        .hasRole("ADMIN")

                        // =========================
                        // PATCH -> SOLO ADMIN
                        // =========================
                        .requestMatchers(HttpMethod.PATCH, "/**")
                        .hasRole("ADMIN")

                        // Cualquier otra petición autenticada
                        .anyRequest().authenticated()
                )

                // CORS
                .cors(Customizer.withDefaults())

                // Basic Auth
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Usuarios en memoria
    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    // Encriptador
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",
                "http://localhost:3000"
        ));

        configuration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH",
                "OPTIONS"
        ));

        configuration.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "Cache-Control"
        ));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}