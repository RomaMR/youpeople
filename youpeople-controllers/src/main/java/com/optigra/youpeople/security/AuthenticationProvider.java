package com.optigra.youpeople.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 05.08.15.
 */
@Component("authenticationProvider")
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProvider.class);

    @Resource(name="userDetailsService")
    private UserDetailsService userDetailsService;

    @Resource(name = "bCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.info("Making authentication for youpeople system");

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String login = token.getName();
        UserDetails user = userDetailsService.loadUserByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exists");
        }
        String encodedPassword = user.getPassword();
        String tokenPassword = (String) token.getCredentials();
        if(!passwordEncoder.matches(tokenPassword, encodedPassword)){
            throw new BadCredentialsException("Invalid username/password");
        }
        return new UsernamePasswordAuthenticationToken(user, tokenPassword,
                user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken
                .class.equals(authentication);
    }
}
