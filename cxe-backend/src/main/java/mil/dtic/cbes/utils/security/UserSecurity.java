package mil.dtic.cbes.utils.security;

import java.security.Principal;

import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurity implements UserDetails, Principal {
    private static final long serialVersionUID = 2676000759332226016L;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    private java.util.Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String role;
    private String commonName;

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
    public boolean implies(Subject subject) {
        return false;
    }
    
    @Override
    public String getName() {
        return getUsername();
    }

    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @Override
    public String toString() {
        return "UserSecurity [authorities="+authorities+", password="+password+", "
                + "username="+username+", accountNonExpired="+accountNonExpired+", "
                + "accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" 
                + credentialsNonExpired+", enabled="+enabled+ "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (accountNonExpired ? 1231 : 1237);
        result = prime * result + (accountNonLocked ? 1231 : 1237);
        result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
        result = prime * result + (credentialsNonExpired ? 1231 : 1237);
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((log == null) ? 0 : log.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserSecurity other = (UserSecurity) obj;
        if (accountNonExpired != other.accountNonExpired)
            return false;
        if (accountNonLocked != other.accountNonLocked)
            return false;
        if (authorities == null) {
            if (other.authorities != null)
                return false;
        } else if (!authorities.equals(other.authorities))
            return false;
        if (credentialsNonExpired != other.credentialsNonExpired)
            return false;
        if (enabled != other.enabled)
            return false;
        if (log == null) {
            if (other.log != null)
                return false;
        } else if (!log.equals(other.log))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
