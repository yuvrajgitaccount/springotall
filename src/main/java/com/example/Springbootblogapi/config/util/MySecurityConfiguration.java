package com.example.Springbootblogapi.config.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebSecurity
@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	
	public static final String[] PUBLIC_URL= {"/generate-token","/register","/v3/api-docs",	
	"/swagger-resources/**",
	"/swagger-ui/**",
	"/webjars/**"
	
	
	};
	
	@Autowired
	private JwtAuthenticationFilter JwtAuthenticationFilter;
	
	
	@Autowired
	private JwtAuthicationEntryPoint unauthorizedHandler;
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	
	@Bean
	 @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	 }
	
	
//	//it is using for plan test
//	@Bean
//
//	public PasswordEncoder passwordEncoder()
//	{
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	
	@Bean
	
 public BCryptPasswordEncoder passwordEncoder()
	{
	return 	new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
		
		
	}
	
	
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable().cors().disable().authorizeRequests().antMatchers(PUBLIC_URL).permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(unauthorizedHandler)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  http.addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
	
	
