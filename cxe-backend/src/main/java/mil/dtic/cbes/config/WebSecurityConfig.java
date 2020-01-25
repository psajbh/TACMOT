package mil.dtic.cbes.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import mil.dtic.cbes.service.user.imp.UserDetailsServiceImpl;
import mil.dtic.cbes.utils.security.CxeHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.trace("configure-");
        http.addFilterBefore(getSsoFilter(), RequestHeaderAuthenticationFilter.class)
        .authenticationProvider(getPreAuthenticationProvider())
        .authorizeRequests()
            .antMatchers("/**").fullyAuthenticated().and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .csrf().disable();
    }
    
    protected void configureGlobal(AuthenticationManagerBuilder auth) {
        log.trace("configureGlobal-");
        auth.authenticationProvider(getPreAuthenticationProvider());  
    }
    
    @Bean
    protected CxeHeaderAuthenticationFilter getSsoFilter() {
        log.trace("getSsoFilter-");
        CxeHeaderAuthenticationFilter filter = new CxeHeaderAuthenticationFilter();
        
        try {
            filter.setAuthenticationManager(super.authenticationManager());
            log.trace("getSsoFilter- setAuthenticationManager");
        } 
        catch(Exception ex) {
            log.error("Authentication Manager exception: " + ex.getMessage(), ex);
        }
      
      return filter;
    }
    
    @Bean
    protected PreAuthenticatedAuthenticationProvider getPreAuthenticationProvider() {
        log.trace("getPreAuthenticationProvider-");
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(getUserDetailsByNameServiceWrapper());
        return provider;
    }

    @Bean
    protected UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> getUserDetailsByNameServiceWrapper() {
        log.trace("getUserDetailsByNameServiceWrapper-");
      UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>();
      wrapper.setUserDetailsService(userDetailsService());
      log.debug("getUserDetailsByNameServiceWrapper- userDetailsService set");
      return wrapper;
    }
    
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        log.trace("userDetailsService-");
        UserDetailsService manager = new UserDetailsServiceImpl();
        return manager;
    }

}


