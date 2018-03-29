package com.zuul.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ComputeController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String service = "Services:" + discoveryClient.getServices();
        Integer r = a + b;
        logger.info("/add, " + service + ", result:" + r);
        return r;
    }

    @RequestMapping(value = "/mul" ,method = RequestMethod.GET)
    public Integer mul(@RequestParam Integer a, @RequestParam Integer b) {
        String service = "Services:" + discoveryClient.getServices();
        Integer r = a * b;
        logger.info("/mul, " + service + ", result:" + r);
        return r;
    }
}
