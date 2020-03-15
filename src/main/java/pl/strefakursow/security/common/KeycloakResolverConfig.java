package pl.strefakursow.security.common;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakResolverConfig {
	@Bean
	public KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
}
