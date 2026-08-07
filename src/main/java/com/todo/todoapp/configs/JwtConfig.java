package com.todo.todoapp.configs;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.todo.todoapp.services.JwtService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.*;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private RSAPublicKey publicKey;

    private RSAPrivateKey privateKey;

    private Duration ttl;

    @Bean
    public JwtEncoder jwtEncoder() {
        final var jwk = new RSAKey.Builder(publicKey)
                .privateKey(privateKey).build();

        return new NimbusJwtEncoder(
                new ImmutableJWKSet<>(new JWKSet(jwk))
        );
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }

    @Bean
    JwtService jwtService(@Value("${spring.application.name}") final String appName,
                          final JwtEncoder jwtEncoder) {
        return new JwtService(appName, ttl, jwtEncoder);
    }
}
