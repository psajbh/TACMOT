package mil.dtic.cbes.service.impl.security;

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
import mil.dtic.cbes.service.user.UserCredentialEntityService;
import mil.dtic.cbes.utils.exceptions.security.CxeUsernameNotFoundException;
import mil.dtic.cbes.utils.exceptions.security.SecurityExceptionMessageHolder;

//note: this class is initialized as a Bean on app startup
public class UserDetailsServiceImpl implements UserDetailsService{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private static final String PASSWORD = "password";
    
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
        log.debug("loadUserByUsername- username: " + username);
        UserEntity userEntity = userEntityRepository.findByUserLdapId(username);

        if (null != userEntity) {
            UserSecurity userDetails = new UserSecurity();
            userDetails.setUsername(username);
            userDetails.setCommonName(userEntity.getFullName());
            userDetails.setPassword(UserDetailsServiceImpl.PASSWORD);
            userDetails.setAuthorities(buildGrantedAuthorities(username));
            userDetails.setAccountNonExpired(true);
            userDetails.setAccountNonLocked(true);
            userDetails.setCredentialsNonExpired(true);
            userDetails.setEnabled(true);
            userDetails.setRole(userEntity.getRole());
            return userDetails;
        }
        log.error("loadUserByUsername- invalid username: " + username);
        throw new CxeUsernameNotFoundException(SecurityExceptionMessageHolder.USER_NOT_FOUND_FAILURE);
    }
    
    private List<GrantedAuthority> buildGrantedAuthorities(String username){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = userCredentialEntityService.getCredentials(username).getRoleName();
        SimpleGrantedAuthority simpleGrantAuthority = new SimpleGrantedAuthority(role);
        grantedAuthorities.add(simpleGrantAuthority);
        return grantedAuthorities;
    }
}
