package com.wu.oauth.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Description : Created by intelliJ IDEA
 * Time : 2018 05 31 下午5:06
 *
 * @author :  wubo
 * @version :  1.0.0
 */
@Component
public class TmAuthenticationProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(TmAuthenticationProvider.class);
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("authentication.Principal:{}",authentication.getPrincipal());
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
                Collections.<GrantedAuthority>emptyList());
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

