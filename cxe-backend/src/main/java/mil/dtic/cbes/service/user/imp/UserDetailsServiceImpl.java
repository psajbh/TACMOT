package mil.dtic.cbes.service.user.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.WebApplicationContext;

import mil.dtic.cbes.model.UserSecurity;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.repositories.UserEntityRepository;
import mil.dtic.cbes.service.user.api.UserCredentialEntityService;

//class instantiated as a @Bean at system startup.
public class UserDetailsServiceImpl implements UserDetailsService{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
    @Autowired
    private WebApplicationContext applicationContext;
    
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserCredentialEntityService userCredentialEntityService;
    

    @PostConstruct
    public void completeSetup() {
        userEntityRepository = applicationContext.getBean(UserEntityRepository.class);
    }
    
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername- start username: " + username);
        UserEntity userEntity = userEntityRepository.findByUserLdapId(username);

        if (null != userEntity) {
            UserSecurity userDetails = new UserSecurity();
            userDetails.setUsername(username);
            userDetails.setCommonName(userEntity.getFullName());
            userDetails.setPassword("password");
            userDetails.setAuthorities(buildGrantedAuthorities(username));
            userDetails.setAccountNonExpired(true);
            userDetails.setAccountNonLocked(true);
            userDetails.setCredentialsNonExpired(true);
            userDetails.setEnabled(true);
            userDetails.setRole(userEntity.getRole());
            return userDetails;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
    
    private List<GrantedAuthority> buildGrantedAuthorities(String username){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = userCredentialEntityService.getCredentials(username).getRoleName();
        SimpleGrantedAuthority simpleGrantAuthority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(simpleGrantAuthority);
        return grantedAuthorities;
    }


}
