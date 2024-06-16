package com.udemy.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 15/06/2024 20:17
 * @Description :
 */
@Configuration
@EnableWebFluxSecurity //Enables Spring Security for WebFlux applications
public class SecurityConfig {

    /**
     * SecurityWebFilterChain: Represents the Spring Security filter chain for WebFlux applications.
     *
     * @param serverHttpSecurity
     * @return
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        /**
         * authorizeExchange: Configures authorization for HTTP requests.
         */
        serverHttpSecurity.authorizeExchange(
                exchange -> exchange.pathMatchers(HttpMethod.GET).permitAll() // Allows all GET requests to be accessed without authentication.
//                        .pathMatchers("/linsbank/accounts/**").authenticated()
//                        .pathMatchers("/linsbank/cards/**").authenticated()
//                        .pathMatchers("/linsbank/loans/**").authenticated()

                        .pathMatchers("/linsbank/accounts/**").hasRole("ACCOUNTS") //Restricts access to paths under /eazybank/accounts/** to users with the ACCOUNTS role.
                        .pathMatchers("/linsbank/cards/**").hasRole("CARDS") //restricts access with Cards role.
                        .pathMatchers("/linsbank/loans/**").hasRole("LOANS")) //restricts access with Loans role.
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());
        return serverHttpSecurity.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor(){
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
