package com.wu.oauth.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchServiceTest {

    @Resource
    private ElasticsearchService elasticsearchService;

    @Test
    public void createIndex() {
        elasticsearchService.createIndex("hello");
    }

    @Test
    public void createDoc() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        elasticsearchService.createDoc("hello",  "doc", jsonMap);
    }
}