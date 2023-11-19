package backendDeAplicaciones.bicicletas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // Permite el acceso a Swagger UI y a los endpoints relacionados
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/configuration/**", "/webjars/**", "/public").permitAll()
                        // Permite el acceso a /get-token
                        .requestMatchers("/get-token").permitAll()
                        // Permite el acceso a todas las demás rutas
                        .anyRequest().permitAll()
                )
                // Deshabilitar CSRF si es una API REST
                .csrf(csrf -> csrf.disable());

        return http.build();
    }



}



