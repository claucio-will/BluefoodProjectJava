package com.claucio.bluefood.infrastructure.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new AuthenticationSuccessHandlerImpl();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/images/*", "/css/**","/js/**", "/public/*", "/sbpay/**").permitAll()
                                .requestMatchers("/client/**").hasRole(Role.CLIENT.toString())
                                .requestMatchers("/restaurante/**").hasRole(Role.RESTAURANTE.toString())
                                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").failureUrl("/login-error")
                        .successHandler(authenticationSuccessHandler())
                        .permitAll()).logout(logout -> logout.logoutUrl("/logout").permitAll());

        return http.build();

    }




}
