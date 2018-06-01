package com.wu.oauth.client.conf;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class OAuth2ClientConfiguration {

    @Resource
    private ResourceServerProperties props;


    @Bean
    public OAuth2RestOperations oauth2RestTemplate(OAuth2ProtectedResourceDetails details) {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        OAuth2RestTemplate template = new OAuth2RestTemplate(details, new DefaultOAuth2ClientContext(atr));
        AuthorizationCodeAccessTokenProvider authCodeProvider = new AuthorizationCodeAccessTokenProvider();
        authCodeProvider.setStateMandatory(false);
        AccessTokenProviderChain provider = new AccessTokenProviderChain(
                Arrays.asList(authCodeProvider));
        template.setAccessTokenProvider(provider);
        return template;
    }

}
