package mil.dtic.cbes.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    public UserSecurity getLoggedInUser() {
        log.trace("getLoggedInUser-");
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserSecurity) {
                UserSecurity userSecurity= (UserSecurity) auth.getPrincipal();
                log.trace("getLoggeddInUser- returning userSecurity: " + userSecurity.toString());
                return userSecurity;
            } else {
                log.error("Principal is not a UserDetails object: " + principal);
            }
        }
        return null;
    }

}
