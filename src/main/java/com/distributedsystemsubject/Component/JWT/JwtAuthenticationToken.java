package com.distributedsystemsubject.Component.JWT;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final String principal;
    @Getter
    private final String role;

    public JwtAuthenticationToken(String principal, String role) {
        super(Collections.singletonList(new SimpleGrantedAuthority(role)));
        this.principal = principal;
        this.role = role;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
