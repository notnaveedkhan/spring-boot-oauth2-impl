package com.example.springbootoauth2resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security, CorsConfiguration corsConfiguration) throws Exception {
        security.cors(it -> it.configurationSource(request -> corsConfiguration));
        security.authorizeHttpRequests(it -> it.anyRequest().authenticated());
        security.csrf(CsrfConfigurer::disable);
        security.httpBasic(HttpBasicConfigurer::disable);
        security.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.oauth2ResourceServer(oauth2 -> {
            oauth2.jwt(Customizer.withDefaults());
        });
        return security.build();
    }

    @Bean
    public CorsConfiguration corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.addAllowedOrigin("/**");
        return configuration;
    }

}
