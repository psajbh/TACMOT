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

import mil.dtic.cbes.model.entities.security.UserEntity;
import mil.dtic.cbes.repositories.security.user.UserEntityRepository;
import mil.dtic.cbes.service.security.user.UserCredentialEntityService;
import mil.dtic.cbes.utils.exceptions.security.CxeUsernameNotFoundException;
import mil.dtic.cbes.utils.security.UserSecurity;

public class UserDetailsServiceImpl implements UserDetailsService {
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
    public UserDetails loadUserByUsername(final String username) throws CxeUsernameNotFoundException {
        if (null == username) {
            throw new CxeUsernameNotFoundException(CxeUsernameNotFoundException.USER_NOT_FOUND_FAILURE);
        }
        UserEntity userEntity = null;
        
        log.debug("loadUserByUsername- start username: " + username);
        try {
            userEntity = userEntityRepository.findByUserLdapId(username);
        }
        catch(Exception e) {
            if (e instanceof UsernameNotFoundException) {
                log.error("loadUserByUsername- UsernameNotFoundException: " + e.getMessage());
                throw new CxeUsernameNotFoundException(e.getMessage());
            }
            else {
                log.error("loadUserByUsername- exception: " + e.getMessage(),e);
                throw new CxeUsernameNotFoundException(CxeUsernameNotFoundException.USER_NOT_FOUND_FAILURE);
            }
        }

        if (null != userEntity) {
            return getCredentialInformation(userEntity);
        }
        
        throw new CxeUsernameNotFoundException(CxeUsernameNotFoundException.USER_NOT_FOUND_FAILURE);
    }
    
    private UserDetails getCredentialInformation(UserEntity userEntity) throws CxeUsernameNotFoundException {
        
        UserSecurity userDetails = new UserSecurity();
        userDetails.setUsername(userEntity.getUserLdapId());
        userDetails.setCommonName(userEntity.getFullName());
        userDetails.setAuthorities(buildGrantedAuthorities(userEntity.getUserLdapId()));
        userDetails.setAccountNonExpired(true);
        userDetails.setAccountNonLocked(true);
        userDetails.setCredentialsNonExpired(true);
        userDetails.setEnabled(true);
        userDetails.setRole(userEntity.getRole());
        return userDetails;
        
    }
    
    private List<GrantedAuthority> buildGrantedAuthorities(String username) throws CxeUsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        
        try {
            String role = userCredentialEntityService.getCredentials(username).getRoleName();
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
            return grantedAuthorities;
        }
        catch(IllegalArgumentException iae) {
            throw new CxeUsernameNotFoundException(CxeUsernameNotFoundException.USER_NO_ROLES_FAILURE);
        }
        catch(Exception e) {
            throw new CxeUsernameNotFoundException(CxeUsernameNotFoundException.INVALID_USER_CREDENTIAL_MSG );
        }
        
    }


}
