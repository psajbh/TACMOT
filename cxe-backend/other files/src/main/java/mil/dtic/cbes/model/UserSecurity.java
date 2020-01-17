package mil.dtic.cbes.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserSecurity implements UserDetails{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private static final long serialVersionUID = 2676000759332226016L;
    
    private java.util.Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialsNonExpired;
    boolean enabled;

    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public void setAuthorities(java.util.Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserSecurity [authorities=" + authorities + ", password=" + password + ", username=" + username + ", accountNonExpired="
                + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled="
                + enabled + "]";
    }
    
    
    

}
