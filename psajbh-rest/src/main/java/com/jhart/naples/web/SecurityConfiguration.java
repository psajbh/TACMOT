package com.jhart.naples.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(getMyAuthenticationFilter(), RequestHeaderAuthenticationFilter.class)
        .authenticationProvider(getPreAuthenticationProvider())
        .authorizeRequests()
            .antMatchers("/**").fullyAuthenticated()
            .and()
            .csrf().disable();
        	//.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
	
	  @Bean
	  protected MyHeaderAuthenticationFilter getMyAuthenticationFilter() {
		  MyHeaderAuthenticationFilter filter = new MyHeaderAuthenticationFilter();
	    try {
	      filter.setAuthenticationManager(authenticationManager());
	    } 
	    catch(Exception ex) {
	      log.debug("Authentication Manager exception: {}");
	    }
	    
	    return filter;
	  }

	  @Bean
	  protected PreAuthenticatedAuthenticationProvider getPreAuthenticationProvider() {
	    PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
	    provider.setPreAuthenticatedUserDetailsService(userDetailsByNameServiceWrapper());
	    return provider;
	  }
	  
	  @Bean
	  protected UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsByNameServiceWrapper() {
	    UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>();
	    wrapper.setUserDetailsService(userDetailsService());
	    return wrapper;
	  }
	  
	  @Bean
	  @Override
	  protected UserDetailsService userDetailsService() {
		  UserDetailsService manager = new UserDetailsManager();
		  return manager;
	  }
	  



}
