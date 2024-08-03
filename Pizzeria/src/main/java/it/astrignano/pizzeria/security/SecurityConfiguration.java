package it.astrignano.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers("/pizzeria/create", "pizzeria/edit/**").hasAuthority("ADMIN")
				.requestMatchers(HttpMethod.POST, "/pizzeria/**").hasAuthority("ADMIN")
				.requestMatchers("/ingredienti", "/ingredienti/**").hasAuthority("ADMIN")
				.requestMatchers("/offerte", "/offerte/**").hasAuthority("ADMIN")
				.requestMatchers("/pizzeria", "/pizzeria/**").hasAnyAuthority("ADMIN", "USER").requestMatchers("/**")
				.permitAll().and().formLogin().and().logout().and().exceptionHandling();

		return http.build();
	}

	
		@Bean
		DatabaseUserDetailService userDetailsService() {
			return new DatabaseUserDetailService();
		}
		
		@Bean
		PasswordEncoder passwordEncoder() {
			return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
		
		@Bean
		DaoAuthenticationProvider authenticatorProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			
			authProvider.setUserDetailsService(userDetailsService());
			authProvider.setPasswordEncoder(passwordEncoder());
			
			return authProvider;
		}
}
