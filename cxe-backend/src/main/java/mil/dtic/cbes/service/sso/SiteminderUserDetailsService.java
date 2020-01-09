package mil.dtic.cbes.service.sso;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.LdapAuthority;
import org.springframework.stereotype.Service;

import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.repositories.UserEntityRepository;

@Service
public class SiteminderUserDetailsService implements UserDetailsService{
    
    private UserEntityRepository userEntityRepository;
    
    public SiteminderUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
    
        //UserEntity user = userEntityRepository.findByUsername(userName);
        UserEntity user = userEntityRepository.findByUserLdapId(userName);
        if (null != user) {
            user.setUsername(userName);
            user.setPassword("password");
            LdapAuthority ldapAuthority = new LdapAuthority("role", user.getRole());
            user.getAuthorities().add(ldapAuthority);
            
            return user;
        }
        throw new UsernameNotFoundException("User '" +userName + "' not found");
        
        
        
    }

}
