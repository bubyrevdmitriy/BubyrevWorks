package com.bubyrevdmitriygmail.LibraryBubyrev.domain;

import org.springframework.security.core.GrantedAuthority;

public enum OrderRole implements GrantedAuthority {
    SELECTED,SENT,ISSUED, EXPIRED, RETURNED;

    @Override
    public String getAuthority() {
        return name();
    }
}