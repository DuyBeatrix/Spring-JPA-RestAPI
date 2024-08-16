package com.buihuuduy.identity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{
    @Autowired
    private CustomJwtDecoder jwtDecoder;

    private final String[] PUBLIC_URLS = {"/users", "/auth/login", "/auth/introspect", "/auth/logout"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        // Authenticate for PUBLIC_REQUEST
        httpSecurity.authorizeHttpRequests (request ->
                request.requestMatchers(HttpMethod.POST, PUBLIC_URLS).permitAll()
        //                .requestMatchers(HttpMethod.GET, "/users").hasAuthority("SCOPE_ADMIN")
                .anyRequest().authenticated()
        );

        // Authenticate for REQUESTS with valid token
        httpSecurity.oauth2ResourceServer (
                oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder))
                        // catch exception from filter
                        .authenticationEntryPoint(new AuthenticationEntryPointImpl())
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }
}
