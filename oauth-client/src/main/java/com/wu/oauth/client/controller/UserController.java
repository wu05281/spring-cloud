package com.wu.oauth.client.controller;

import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private OAuth2RestOperations oAuth2RestOperations;

    @RequestMapping("/user")
    public Object user() {
        return oAuth2RestOperations.getForObject("http://localhost:8081/api/user" ,Map.class);
    }

    @RequestMapping("/callback")
    public String callback() {
        return "hello";
    }
}
