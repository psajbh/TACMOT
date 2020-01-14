package mil.dtic.cbes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@ComponentScan("mil.dtic.cbes")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired  
    private UserDetailsService userDetailsService;
    
//    @Autowired
//    public void configureGlobalAuth(final AuthenticationManagerBuilder auth) throws Exception{
//    	log.debug("configure- auth start");
//    	auth.userDetailsService(userDetailsService);
//    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

	@Override
    public void configure(final HttpSecurity http) throws Exception {
		
		log.debug("configure- http start");
		http.
		//authorizeRequests().antMatchers("^/login.**").anonymous().
		authorizeRequests().antMatchers("^/login.**").fullyAuthenticated().
		and().httpBasic().
		and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().csrf().disable();
    }
	  
}

//Notes:
//The WebSecurityConfig class is annotated with @EnableWebSecurity to enable Spring Security’s web security support and 
//provide the Spring MVC integration. It also extends WebSecurityConfigurerAdapter and overrides a couple of its methods 
//to set some specifics of the web security configuration
//https://www.baeldung.com/spring-security-method-security