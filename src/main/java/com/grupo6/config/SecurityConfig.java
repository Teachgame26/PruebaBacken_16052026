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
            // Deshabilita CSRF para APIs REST
            .csrf(csrf -> csrf.disable())

            // Configuración de permisos
            .authorizeHttpRequests(auth -> auth

                // Rutas públicas
                .requestMatchers(
                    "/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/scalar/**",
                    "/index.html",
                    "/"
                ).permitAll()

                // GET -> USER y ADMIN
                .requestMatchers(HttpMethod.GET, "/estudiantes/**")
                    .hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/profesores/**")
                    .hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/materias/**")
                    .hasAnyRole("ADMIN", "USER")

                .requestMatchers(HttpMethod.GET, "/usuarios/**")
                    .hasAnyRole("ADMIN", "USER")

                // POST -> SOLO ADMIN
                .requestMatchers(HttpMethod.POST, "/estudiantes/**")
                    .hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/profesores/**")
                    .hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/materias/**")
                    .hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/usuarios/**")
                    .hasRole("ADMIN")

                // PUT -> SOLO ADMIN
                .requestMatchers(HttpMethod.PUT, "/**")
                    .hasRole("ADMIN")

                // DELETE -> SOLO ADMIN
                .requestMatchers(HttpMethod.DELETE, "/**")
                    .hasRole("ADMIN")

                // PATCH -> SOLO ADMIN
                .requestMatchers(HttpMethod.PATCH, "/**")
                    .hasRole("ADMIN")

                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )

            // Habilitar CORS
            .cors(Customizer.withDefaults())

            // Habilitar Basic Auth
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

    // Encriptador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Permitir frontend local
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",
                "http://localhost:3000"
        ));

        // Métodos permitidos
        configuration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "PATCH",
                "OPTIONS"
        ));

        // Headers permitidos
        configuration.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Cache-Control",
                "Content-Type"
        ));

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
