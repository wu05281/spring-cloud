package com.wu.oauth.resource.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description : Created by intelliJ IDEA
 * Time : 2018 06 01 下午3:00
 *
 * 坑在这里太久了，如果不实现这个类，访问资源会报错'Full authentication is required to access this resource'
 * 如果实现这个类，但是不写web.ignoring().antMatchers("/favor.ico","/api/user")，则会直接跳转至本应用的登陆页
 *
 * @author :  wubo
 * @version :  1.0.0
 */
@Configuration
public class ResWebSecurityConfigurerAdapter  extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {


        web.ignoring().antMatchers("/favor.ico","/api/user");
    }
}
