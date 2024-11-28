package com.claucio.bluefood.infrastructure.web.security;

import com.claucio.bluefood.util.SecurityUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Role role = SecurityUtils.loggedUser().getRole();

        if (role == Role.CLIENT){
            response.sendRedirect("client/home");
        } else if (role == Role.RESTAURANTE) {
            response.sendRedirect("restaurante/home");
        }else {
            throw new IllegalStateException("Erro na autenticação");
        }

    }
}
