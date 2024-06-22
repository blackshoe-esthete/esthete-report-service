package org.example.blackshoe.esthetereportservice.security;

import org.example.blackshoe.esthetereportservice.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

//user 객체 생성
public class UserPrincipal implements UserDetails {
    private Admin admin;

    public UserPrincipal(Admin admin) {
        super();
        this.admin = admin;
    }

    public Admin getAdmin() {
        return this.admin;
    }

    public UUID getAdminId() {
        return this.admin.getAdminId();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(admin.getRole().getRoleName()));
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getEmail();
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

    public static UserPrincipal create(Admin user) {
        return new UserPrincipal(user);
    }
}

