package com.wu.oauth.client.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class OAuth2ClientConfiguration {

    @Value("${security.oauth2.client.access-token-uri:}")
    String accessTokenURI;
    @Value("${security.oauth2.client.client-id:}")
    String clientID;
    @Value("${security.oauth2.client.client-secret:}")
    String clientSecret;
    @Value("${security.oauth2.client.client-authentication-scheme:}")
    String clientAuthenticationScheme;
    @Value("${security.oauth2.client.grant-type:}")
    String grantType;

    @Bean
    ClientCredentialsResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(accessTokenURI);
        details.setClientId(clientID);
        details.setClientSecret(clientSecret);
        details.setGrantType(grantType);
        details.setAuthenticationScheme(AuthenticationScheme.valueOf(clientAuthenticationScheme));
        return details;
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), new DefaultOAuth2ClientContext());
        return restTemplate;
    }
}
