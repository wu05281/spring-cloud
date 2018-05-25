package com.wu.oauth.server.service;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;


@Service
public class ElasticsearchService {

    @Resource
    private RestHighLevelClient transportClient;

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
    public boolean createIndex(String index) {
        CreateIndexRequest request = new CreateIndexRequest(index);//index名必须全小写，否则报错
        request.settings(createSetting());
        CreateIndexResponse response = null;
        try {
            response = transportClient.indices().create(request);
            } catch (IOException e) {
             e.printStackTrace();
            }
        return response.isShardsAcknowledged();
    }

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
    public boolean createDoc(String index, String type, Map<String, Object> jsonMap) {
        IndexRequest request = new IndexRequest(index, type);//index名必须全小写，否则报错
        request.source(jsonMap);
        IndexResponse response = null;
        try {
            response = transportClient.index(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.isFragment();
    }

    // 设置index的Settings
    public static Settings createSetting() {
        Settings settings = Settings.builder().put("number_of_shards", 4).put("number_of_replicas", 0).build();
        return settings;
    }

}
