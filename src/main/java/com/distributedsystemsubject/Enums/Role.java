package com.distributedsystemsubject.Enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER"),;

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
