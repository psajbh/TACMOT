package mil.dtic.cbes.utils.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import mil.dtic.cbes.utils.security.UserSecurity;

public class UserSecurityTest {
	
	UserSecurity userSecurity = new UserSecurity();
	
	@BeforeEach
	public void setup() {
		createUserSecurity();
	}

	@Test
	public void testUserSecurity() {
		assertNotNull(userSecurity.getName());
		assertNotNull(userSecurity.getAuthorities());
		assertNotNull(userSecurity.getPassword());
		assertNotNull(userSecurity.getUsername());
		assertNotNull(userSecurity.isAccountNonExpired());
        String testToString = userSecurity.toString();
        assertNotNull(testToString);
        
        assertNotNull(userSecurity.toString());
        assertNotNull(userSecurity.hashCode());
        assertTrue(userSecurity.isEnabled());
	}
	
	@Test 
	public void testMoreUserSecurity() {
		userSecurity.setAccountNonExpired(false);
		userSecurity.setAccountNonLocked(false);
		userSecurity.setCredentialsNonExpired(false);
		userSecurity.setEnabled(false);
		userSecurity.setPassword(null);
		userSecurity.setUsername(null);
		assertNotNull(userSecurity.isAccountNonExpired());
        assertNotNull(userSecurity.hashCode());
        assertFalse(userSecurity.isEnabled());
        assertFalse(userSecurity.implies(null));
	}
	
	@Test
	public void equalsTest() {
		UserSecurity userSecurity1 = createUserSecurityInstance();
		UserSecurity userSecurity2 = createUserSecurityInstance();
		assertTrue(userSecurity1.equals(userSecurity2));
		
		UserSecurity userSecurity3 = null;
		assertFalse(userSecurity1.equals(userSecurity3));
		
		userSecurity3 = new UserSecurity();
		userSecurity3.setUsername("notTheSameName");
		assertFalse(userSecurity1.equals(userSecurity3));
		
		UserSecurity userSecurity4 = userSecurity1;
		assertTrue(userSecurity1.equals(userSecurity4));
		
		assertFalse(userSecurity1.equals("test"));
		
		userSecurity1.setUsername(null);
		assertFalse(userSecurity1.equals(userSecurity3));
		
		userSecurity3.setUsername(null);
		assertFalse(userSecurity1.equals(userSecurity3));
	
//		EqualsVerifier.forClass(UserSecurity.class).suppress(Warning.STRICT_INHERITANCE)
//		.suppress(Warning.NONFINAL_FIELDS).verify();
	}
	
	private void createUserSecurity() {
		userSecurity = new UserSecurity();
		userSecurity.setRole("R2_User");
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userSecurity.getRole()));
		userSecurity.setAuthorities(grantedAuthorities);
		userSecurity.setPassword("PASSWORD");
		userSecurity.setUsername("username");
		userSecurity.setAccountNonExpired(true);
		userSecurity.setAccountNonLocked(true);
		userSecurity.setCredentialsNonExpired(true);
		userSecurity.setEnabled(true);
	}
	
	private UserSecurity createUserSecurityInstance() {
		userSecurity = new UserSecurity();
		userSecurity.setRole("R2_User");
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userSecurity.getRole()));
		userSecurity.setAuthorities(grantedAuthorities);
		userSecurity.setPassword("PASSWORD");
		userSecurity.setUsername("USERNAME");
		userSecurity.setAccountNonExpired(true);
		userSecurity.setAccountNonLocked(true);
		userSecurity.setCredentialsNonExpired(true);
		userSecurity.setEnabled(true);
		return userSecurity;
	}

}
