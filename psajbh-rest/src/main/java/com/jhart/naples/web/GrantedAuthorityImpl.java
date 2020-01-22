package com.jhart.naples.web;

import org.springframework.security.core.GrantedAuthority;

import com.jhart.naples.rest.Role;


public class GrantedAuthorityImpl implements GrantedAuthority {
	private Role role;
	
    @Override
    public String getAuthority() {
        return role.getName();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "GrantedAuthorityImpl [role=" + role + "]";
    }

}
