package pl.strefakursow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AlwaysAllowFilter alwaysAllowFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").addFilterAfter(alwaysAllowFilter,
			BasicAuthenticationFilter.class).authorizeRequests()
			.anyRequest().authenticated().and().formLogin();
	}

	@Configuration
	public static class SecurityContextHolderConfig {
		@Bean
		public SecurityContextHolderStrategy securityContextHolderStrategy() {
			return SecurityContextHolder.getContextHolderStrategy();
		}
	}

	/*@Override
	protected void configure(
		AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(new AuthenticationProvider() {
			@Override
			public Authentication authenticate(
				Authentication authentication) throws
				AuthenticationException {
				return new UsernamePasswordAuthenticationToken(
					"any principal", "any credentials",
					List.of(new SimpleGrantedAuthority(
						"USER")));
			}

			@Override
			public boolean supports(Class<?> aClass) {
				return true;
			}
		});
	}*/
}
