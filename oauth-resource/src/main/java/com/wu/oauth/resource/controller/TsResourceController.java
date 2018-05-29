package com.wu.oauth.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TsResourceController {
    @RequestMapping("/api/test1")
    public Object test1() {
        Map<String, Object> m = new HashMap<>();
        m.put("method", "test1");
        m.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return m;
    }
    @RequestMapping("/api/user")
    public Object user() {
        Map<String, Object> m = new HashMap<>();
        m.put("method", "user");
        m.put("name", "皇太极");
        m.put("age", 18);
        m.put("sex", "男");
        m.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return m;
    }
}
