package mil.dtic.cbes.service.user.imp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;
import mil.dtic.cbes.model.UserSecurity;
import mil.dtic.cbes.model.entities.UserEntity;
import mil.dtic.cbes.repositories.UserEntityRepository;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private WebApplicationContext applicationContext;

	private UserEntityRepository userEntityRepository;

	public UserDetailsServiceImpl() {
		super();
	}

	@PostConstruct
	public void completeSetup() {
		userEntityRepository = applicationContext.getBean(UserEntityRepository.class);
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername- start username: " + username);
		UserEntity userEntity = userEntityRepository.findByUserLdapId(username);

		if (null != userEntity) {
			UserSecurity userDetails = new UserSecurity();
			userDetails.setUsername(username);
			userDetails.setPassword("password");
			userDetails.setAuthorities(null);
			userDetails.setAccountNonExpired(true);
			userDetails.setAccountNonLocked(true);
			userDetails.setCredentialsNonExpired(true);
			userDetails.setEnabled(true);
			return userDetails;
		}
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}

}
