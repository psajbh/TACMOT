package com.jhart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//Spring REST + Spring Security Example|https://www.mkyong.com/spring-boot/spring-rest-spring-security-example/]]
@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http
	                //HTTP Basic authentication
	                .httpBasic()
	                .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/userDataTable/**").hasRole("USER")
				/*
				 * .antMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
				 * .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
				 * .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
				 * .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
				 */	                
	                .and()
	                .csrf().disable()
	                .formLogin().disable();
	    }
	
	

}
