package mil.dtic.cbes.controllers;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/api")
public class BaseRestController {

//    protected UserCredentialDto getCredential() {
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
//        return (UserCredentialDto) request.getAttribute(CredentialsAspect.CREDENTIAL_KEY_ATTRIBUTE);
//    }
//	
//	protected static final String getLdapId() {
//		return ThreadContext.get(CxeHeaderAuthenticationFilter.MDC_KEY_USER_NAME);
//	}
    
}
