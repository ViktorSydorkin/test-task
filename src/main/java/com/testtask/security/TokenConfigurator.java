package com.testtask.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@AllArgsConstructor
public class TokenConfigurator extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        TokenFilter jwtTokenFilter = new TokenFilter(tokenProvider);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
