package com.claucio.bluefood.infrastructure.web.security;

import com.claucio.bluefood.domain.client.Client;
import com.claucio.bluefood.domain.restaurante.Restaurante;
import com.claucio.bluefood.domain.user.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class LoggedUser implements UserDetails {

    @Getter
    private User user;
    @Getter
    private Role role;
    private Collection<? extends GrantedAuthority> roles;

    public LoggedUser(User user) {
        this.user = user;
        Role role;

        if (user instanceof Client){
            role = Role.CLIENT;
        } else if (user instanceof Restaurante) {
            role = Role.RESTAURANTE;
        }else {
            throw new IllegalMonitorStateException("Tipo de usuário não é valido");
        }
        this.role = role;
        this.roles = List.of(new SimpleGrantedAuthority("ROLE" + role));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


}
