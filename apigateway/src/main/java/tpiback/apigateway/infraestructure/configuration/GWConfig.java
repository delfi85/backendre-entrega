package tpiback.apigateway.infraestructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${api-gw.url-microservicio-estaciones}") String uriEstaciones,
                                        @Value("${api-gw.url-microservicio-alquileres}") String uriAlquileres) {
        return builder.routes()

                .route(p -> p.path("/alquileres/**").uri(uriAlquileres))
                .route(p -> p.path("/estaciones/**").uri(uriEstaciones))
                .build();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(exchanges -> exchanges
                        //Cliente
                        .pathMatchers(HttpMethod.POST,"/alquileres/nuevo")
                        .hasRole("CLIENTE")

                        .pathMatchers(HttpMethod.POST,"/alquileres/finalizar")
                        .hasRole("CLIENTE")


                        .pathMatchers(HttpMethod.GET,"/alquileres/cliente/**")
                        .hasRole("CLIENTE")

                        .pathMatchers(HttpMethod.GET,"/estaciones/all")
                        .hasRole("CLIENTE")

                        .pathMatchers(HttpMethod.GET,"/ubicacion/estacion-mas-cercana")
                        .hasRole("CLIENTE")

                        //Administrador

                        .pathMatchers(HttpMethod.POST,"/estaciones")
                        .hasRole("ADMINISTRADOR")


                        .pathMatchers("/alquileres/**")
                        .hasRole("ADMINISTRADOR")

                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Se especifica el nombre del claim a analizar
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        // Se agrega este prefijo en la conversión por una convención de Spring
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        // Se asocia el conversor de Authorities al Bean que convierte el token JWT a un objeto Authorization
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuthoritiesConverter));
        // También se puede cambiar el claim que corresponde al nombre que luego se utilizará en el objeto
        // Authorization
        // jwtAuthenticationConverter.setPrincipalClaimName("user_name");

        return jwtAuthenticationConverter;
    }
}

