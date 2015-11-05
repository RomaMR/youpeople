package com.optigra.youpeople.configuration;

import com.optigra.youpeople.security.RestAuthenticationEntryPoint;
import com.optigra.youpeople.web.commons.RMPath;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 05.08.15.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "authenticationEntryPoint")
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Resource(name = "authenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers(RMPath.Documentation.ROOT).permitAll()
                .antMatchers(RMPath.User.ROOT + RMPath.User.REGISTRATION).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable()
                .authenticationProvider(authenticationProvider);
    }
}
