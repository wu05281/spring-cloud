package com.wu.oauth.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/user")
    public Object user() {
        return restTemplate.getForObject("http://localhost:8081/api/user",Map.class);
    }
}
