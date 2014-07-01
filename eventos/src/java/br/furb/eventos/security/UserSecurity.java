
package br.furb.eventos.security;

import java.security.Principal;
import java.util.Set;

public class UserSecurity implements Principal {
    private String name;
    private Set roles;
    
    public UserSecurity(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Set getRoles () {
        return roles;
    }
    
    public void setRoles (Set roles) {
        if (this.roles == null)
            this.roles = roles;
    }
}
