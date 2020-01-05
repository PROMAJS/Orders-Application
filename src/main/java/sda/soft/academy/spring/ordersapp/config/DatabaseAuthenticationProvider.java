package sda.soft.academy.spring.ordersapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import sda.soft.academy.spring.ordersapp.services.LoginService;

import java.util.ArrayList;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        boolean logged = loginService.login(login, password);
        if (logged) {
            return new UsernamePasswordAuthenticationToken(login, password, new ArrayList<>());
        } else {
            throw new RuntimeException("User with login "+login+" not logged");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
