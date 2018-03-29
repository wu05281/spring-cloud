package com.wu.cloud.feign.service;


import org.springframework.stereotype.Component;

@Component("computeClientF")
public class ComputeClientFallback  implements ComputeClient {
    @Override
    public Integer add(Integer a, Integer b) {
        return Integer.MIN_VALUE;
    }
}
