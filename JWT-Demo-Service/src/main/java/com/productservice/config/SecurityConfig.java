package com.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.productservice.model.UserInfo;
import com.productservice.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() { // Authentication

		/*UserDetails admin = User.withUsername("surya").password(passwordEncoder.encode("asd")).roles("ADMIN").build();

		UserDetails user = User.withUsername("jeet").password(passwordEncoder.encode("asd")).roles("USER").build();

		return new InMemoryUserDetailsManager(admin, user); // to keep user in In-Memory DB */
		
		return new UserInfoUserDetailsService();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome","/products/new", "/products/authenticate").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/products/**")
                .authenticated().and().formLogin().and().build();
    }
	
	@Bean
	public AuthenticationProvider authenticationProvider() { // understand in detail
		DaoAuthenticationProvider daoAuthencationProvider = new DaoAuthenticationProvider();
		daoAuthencationProvider.setUserDetailsService(userDetailsService());
		daoAuthencationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthencationProvider;
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {		
		return config.getAuthenticationManager();
	}
	
}
