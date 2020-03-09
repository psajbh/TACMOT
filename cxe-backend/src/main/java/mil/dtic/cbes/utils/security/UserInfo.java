package mil.dtic.cbes.utils.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.utils.aspect.CredentialsAspect;

@Component
public class UserInfo {
	
	public UserCredentialDto getCredential() {
		  HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		  return (UserCredentialDto) request.getAttribute(CredentialsAspect.CREDENTIAL_KEY_ATTRIBUTE);
	}
	
	public final String getUserLdapId() {
		return ThreadContext.get(CxeHeaderAuthenticationFilter.MDC_KEY_USER_NAME);
	}

}
