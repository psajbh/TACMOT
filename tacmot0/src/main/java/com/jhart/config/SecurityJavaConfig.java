package com.jhart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

//Spring REST + Spring Security Example|https://www.mkyong.com/spring-boot/spring-rest-spring-security-example/]]
//Spring Boot: How to specify the PasswordEncoder?|https://stackoverflow.com/questions/46999940/spring-boot-how-to-specify-the-passwordencoder]]
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.authorizeRequests()
	.antMatchers("/todo/index")
	.hasRole("USER")
	.antMatchers("/**").permitAll();
	}	

	// note: using bullshit which is nonexistent in order to bypass security for the
	// MVC app.
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http
//				// HTTP Basic authentication
//				.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/bullshit").hasRole("USER")
//				// curl localhost:8080/todoDataTable -u user:password
//				/*
//				 * .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
//				 * .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
//				 * .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
//				 * .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
//				 */
//				.and().csrf().disable().formLogin().disable();
//	}

}
