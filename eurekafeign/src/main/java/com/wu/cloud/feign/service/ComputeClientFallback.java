package com.wu.cloud.feign.service;


import org.springframework.stereotype.Component;

@Component
public class ComputeClientFallback  implements ComputeClient {
    @Override
    public Integer add(Integer a, Integer b) {
        return null;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        return null;
    }
}
