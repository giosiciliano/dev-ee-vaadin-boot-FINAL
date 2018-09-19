package com.gio.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		final DaoAuthenticationProvider authenticateProvider = new DaoAuthenticationProvider();
		authenticateProvider.setUserDetailsService(userDetailsService);
		authenticateProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		auth.userDetailsService(userDetailsService).and().authenticationProvider(authenticateProvider);
	}
	
	// what pages that will be authenticated
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// cross site request forgery (prevents this) - thanks Spring
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(
			new LoginUrlAuthenticationEntryPoint("/login"))
		// all these URLs are allowed to public
			.and().authorizeRequests().antMatchers("/VAADIN/**","/PUSH/**",
					"/UIDL/**",
					"/login",
					"/signup",
					"/login/**",
					"/logout",
					"/vaadinServlet/**").permitAll()
		// these URLs require authentication
			.antMatchers("/ui",
					"/ui/**").fullyAuthenticated();
		
	}
	
	@Bean
	public DaoAuthenticationProvider createDaoAuthenticateProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(pwEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
