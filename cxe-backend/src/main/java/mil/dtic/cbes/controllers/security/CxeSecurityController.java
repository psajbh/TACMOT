package mil.dtic.cbes.controllers.security;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mil.dtic.cbes.controllers.BaseRestController;
import mil.dtic.cbes.model.UserSecurity;
import mil.dtic.cbes.utils.exceptions.security.AccountNotFoundException;
import mil.dtic.cbes.utils.exceptions.security.SecurityExceptionMessageHolder;
import mil.dtic.cbes.utils.security.Authorization;
import mil.dtic.cbes.utils.security.LoginManager;

@RestController
public class CxeSecurityController extends BaseRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String LOGIN_ID = "loginId";
	private static final String NAME = "name";
	private static final String IS_AUTHENTICATED = "isAuthenticated";
	private static final String ROLE = "role";
	private static final String ACCOUNT_NON_EXPIRED = "accountNonExpired";
	private static final String CREDENTIAL_NON_EXPIRED = "credentialsNonExpired";
	private static final String ACCOUNT_NON_LOCKED = "accountNonLocked";
	private static final String AUTHORITIES = "authorities";

	private final Authorization auth;

	private LoginManager loginManager;

	public CxeSecurityController(Authorization auth, LoginManager loginManager) {
		this.auth = auth;
		this.loginManager = loginManager;
	}

	@GetMapping("/user/auth")
	public ResponseEntity<Map<String, Object>> getUser(Principal user) throws AccountNotFoundException {
		
		if (null == user) {
			log.error("getUser- user is null");
			throw new AccountNotFoundException(SecurityExceptionMessageHolder.INVALID_PRINCIPAL);
		}
		log.trace("getUser- principal: " + user.getName());
		Map<String, Object> map = new LinkedHashMap<>();
		UserSecurity userSecurity = loginManager.getLoggedInUser();

		if (null == auth || null == userSecurity) {
			log.error("getUser- auth or userSecurity are null");
			if (null == auth) {
				throw new AccountNotFoundException(SecurityExceptionMessageHolder.INVALID_AUTHORIZATION);
			}
			throw new AccountNotFoundException(SecurityExceptionMessageHolder.INVALID_USER_SECURITY);

		} 
		else {
			map.put(CxeSecurityController.LOGIN_ID, userSecurity.getUsername());
			map.put(CxeSecurityController.NAME, userSecurity.getCommonName());
			map.put(CxeSecurityController.IS_AUTHENTICATED, auth.isAuthenticated(user));
			map.put(CxeSecurityController.ROLE, userSecurity.getRole());
			map.put(CxeSecurityController.ACCOUNT_NON_EXPIRED, userSecurity.isAccountNonExpired());
			map.put(CxeSecurityController.CREDENTIAL_NON_EXPIRED, userSecurity.isCredentialsNonExpired());
			map.put(CxeSecurityController.ACCOUNT_NON_LOCKED, userSecurity.isAccountNonLocked());
			map.put(CxeSecurityController.AUTHORITIES, userSecurity.getAuthorities());
			log.trace("getUser- returning: " + map);
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}

	}

}
