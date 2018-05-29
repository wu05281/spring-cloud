package com.wu.oauth.client.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;


import javax.annotation.Resource;

@Configuration
public class OAuth2ClientConfiguration {

    @Resource
    private OAuth2ProtectedResourceDetails auth2ProtectedResourceDetails;

    @Resource
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public OAuth2RestOperations restTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(auth2ProtectedResourceDetails, oAuth2ClientContext);
        return restTemplate;
    }
}
