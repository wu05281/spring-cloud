package com.wu.oauth.resource.conf;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import javax.annotation.Resource;

/**
 * Description : Created by intelliJ IDEA
 * Time : 2018 06 11 下午3:35
 *
 * @author :  wubo
 * @version :  1.0.0
 */
@Configuration
@EnableResourceServer
public class ResServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private ResourceServerProperties props;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(props.getResourceId()).stateless(true);

        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("client");
        remoteTokenServices.setClientSecret("security");
        remoteTokenServices.setCheckTokenEndpointUrl(props.getTokenInfoUri());
        remoteTokenServices.setAccessTokenConverter(new DefaultAccessTokenConverter());
        resources.tokenServices(remoteTokenServices);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//         Since we want the protected resources to be accessible in the UI as well we need
//         session creation to be allowed (it's disabled by default in 2.0.6)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/**").authenticated();
        httpSecurity.csrf().disable();

    }
}
