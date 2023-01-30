package com.greatlearning.labrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	public ApplicationSecurityConfiguration(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	/**
	 * This method is to setup the Authorization
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/students/**", "/api**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.POST, "/api/students/**", "/api**").hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/students/**", "/api**").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/students/**", "/api**").hasAnyRole("ADMIN")
				.anyRequest()
				.fullyAuthenticated().and().httpBasic();

	}

	/**
	 * This method is to setup the authentication
	 */
	@Override
	public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
