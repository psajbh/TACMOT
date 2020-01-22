package com.jhart.naples.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jhart.naples.rest.User;

public class UserDetailsImpl implements UserDetails{
	
	private User user;

	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        List<GrantedAuthorityImpl> authorities = new ArrayList<GrantedAuthorityImpl>();
	        GrantedAuthorityImpl authority = new GrantedAuthorityImpl();
	        authority.setRole(user.getRole());
	        authorities.add(authority);
	        return authorities;
	    }

	    @Override
	    public String getPassword() {
	        return null;
	    }

	    @Override
	    public String getUsername() {
	        return user.getUsername();
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return true;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    @Override
	    public String toString() {
	        return "UserDetailsImpl [user=" + user + "]";
	    }
	    
}
