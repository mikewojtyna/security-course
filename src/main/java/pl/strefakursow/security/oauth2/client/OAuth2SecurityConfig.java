package pl.strefakursow.security.oauth2.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.strefakursow.security.common.KeycloakResolverConfig;

@Configuration
@Import(KeycloakResolverConfig.class)
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and()
			.oauth2Login();
	}
}
