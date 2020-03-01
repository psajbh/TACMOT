package mil.dtic.cbes.controllers;


import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.utils.aspect.CredentialsAspect;
import mil.dtic.cbes.utils.security.CxeHeaderAuthenticationFilter;

@Component
@RequestMapping("/api")
public class BaseRestController {

    protected UserCredentialDto getCredential() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return (UserCredentialDto) request.getAttribute(CredentialsAspect.CREDENTIAL_KEY_ATTRIBUTE);
    }
	
	protected static final String getLdapId() {
		return ThreadContext.get(CxeHeaderAuthenticationFilter.MDC_KEY_USER_NAME);
	}
    
}
