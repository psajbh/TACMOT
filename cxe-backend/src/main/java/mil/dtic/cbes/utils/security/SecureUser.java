package mil.dtic.cbes.utils.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mil.dtic.cbes.model.dto.security.UserCredentialDto;
import mil.dtic.cbes.utils.aspect.CredentialsAspect;

//singleton example
@Deprecated
public class SecureUser {
	
	private SecureUser() {}
	
	private static class SecurityInfo {
		private static final SecureUser INSTANCE = new SecureUser();
	}
	
	public static SecureUser getInstance() {
		return SecurityInfo.INSTANCE;
	}
	
	public  static UserCredentialDto getCredential() {
	  HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	  return (UserCredentialDto) request.getAttribute(CredentialsAspect.CREDENTIAL_KEY_ATTRIBUTE);
  }

  public static final String getLdapId() {
	return ThreadContext.get(CxeHeaderAuthenticationFilter.MDC_KEY_USER_NAME);
  }


}
