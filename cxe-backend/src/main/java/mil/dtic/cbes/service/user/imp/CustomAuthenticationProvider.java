package mil.dtic.cbes.service.user.imp;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;


public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	 @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 authentication.getName();
		 return null;
	  
//	        String name = authentication.getName();
//	        String password = authentication.getCredentials().toString();
//	         
//	        if (shouldAuthenticateAgainstThirdPartySystem()) {
//	  
//	            // use the credentials
//	            // and authenticate against the third-party system
//	            return new UsernamePasswordAuthenticationToken(
//	              name, password, new ArrayList<>());
//	        } else {
//	            return null;
//	        }
	    }
	 
	    @Override
	    public boolean supports(Class<?> authentication) {
	        //return authentication.equals(UsernamePasswordAuthenticationToken.class);
	    	return true;
	    }

}