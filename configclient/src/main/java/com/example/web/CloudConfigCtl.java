package com.example.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wubo on 2016/10/20.
 */
@RefreshScope
@RestController
public class CloudConfigCtl {

    @Value("{name}")
    private String name;

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(){
        return name;
    }
}
