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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.trace("configure- initialization");
		http.addFilterBefore(getMyHeaderAuthenticationFilter(), RequestHeaderAuthenticationFilter.class)
				.authenticationProvider(getPreAuthenticationProvider()).authorizeRequests().antMatchers("/**")
				.fullyAuthenticated().and().csrf().disable();
		// .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	@Bean
	protected MySsoHeaderAuthenticationFilter getMyHeaderAuthenticationFilter() {
		log.trace("MyHeaderAuthenticationFilter- initialization");
		MySsoHeaderAuthenticationFilter filter = new MySsoHeaderAuthenticationFilter();
		try {
			filter.setAuthenticationManager(authenticationManager());
		} 
		catch (Exception ex) {
			log.debug("Authentication Manager exception: {}");
		}

		return filter;
	}

	@Bean
	protected PreAuthenticatedAuthenticationProvider getPreAuthenticationProvider() {
		log.trace("PreAuthenticatedAuthenticationProvider- initialization");
		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
		provider.setPreAuthenticatedUserDetailsService(getUserDetailsByNameServiceWrapper());
		return provider;
	}

	@Bean
	protected UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> getUserDetailsByNameServiceWrapper() {
		log.trace("UserDetailsByNameServiceWrapper- initialization");
		UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>();
		wrapper.setUserDetailsService(userDetailsService());
		return wrapper;
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		log.trace("UserDetailsService- initialization");
		UserDetailsService manager = new UserDetailsManager();
		return manager;
	}

}
