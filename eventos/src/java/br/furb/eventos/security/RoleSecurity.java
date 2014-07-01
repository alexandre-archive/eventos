package br.furb.eventos.security;

import java.security.Principal;

public class RoleSecurity implements Principal {

    private String name;

    public RoleSecurity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
