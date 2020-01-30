package pl.strefakursow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().anyRequest()
			.authenticated().and().httpBasic();
	}

	@Configuration
	public static class SecurityContextHolderConfig {
		@Bean
		public SecurityContextHolderStrategy securityContextHolderStrategy() {
			return SecurityContextHolder.getContextHolderStrategy();
		}
	}
}
