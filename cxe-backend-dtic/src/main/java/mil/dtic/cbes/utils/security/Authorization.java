package mil.dtic.cbes.utils.security;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class Authorization {
    
    public boolean isAuthenticated(Principal principal) {
        if(principal == null) {
          return false;
        }
        Authentication currentUser = (Authentication) principal;
        return currentUser.isAuthenticated();
      }


}
