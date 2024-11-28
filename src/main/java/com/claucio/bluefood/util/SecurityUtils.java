package com.claucio.bluefood.util;

import com.claucio.bluefood.domain.client.Client;
import com.claucio.bluefood.domain.restaurante.Restaurante;
import com.claucio.bluefood.infrastructure.web.security.LoggedUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {


    //Verifica se existe algum usuário logado no sistema
    public static LoggedUser loggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (LoggedUser) authentication.getPrincipal();
    }


    //Verifica se o usuário logado é um cliente
    public Client loggedClient() {
        LoggedUser loggedUser = loggedUser();

        if (loggedUser == null) {
            throw new IllegalStateException("Não existe usuário logado");
        }

        if (!(loggedUser.getUser() instanceof Client)) {
            throw new IllegalStateException("Usuário logado não é um cliente");
        }

        return (Client) loggedUser.getUser();
    }


    //Verifica se o usuário logado é um restaurante.
    public Restaurante loggedRestaurante() {
        LoggedUser loggedUser = loggedUser();

        if (loggedUser == null) {
            throw new IllegalStateException("Não existe usuário logado");
        }

        if (!(loggedUser.getUser() instanceof Restaurante)) {
            throw new IllegalStateException("Usuário logado não é um restaurante");
        }

        return (Restaurante) loggedUser.getUser();
    }
}
