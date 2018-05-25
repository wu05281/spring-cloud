package com.wu.oauth.server.conf;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

    private String host = "172.17.246.53";
    private int port = 9200;
    private String schema = "http";
    private int connectTimeOut = 1000;
    private int socketTimeOut = 30000;
    private int connectionRequestTimeOut = 500;

    private int maxConnectNum = 100;
    private int maxConnectPerRoute = 100;

    private HttpHost httpHost = new HttpHost(host, port, schema);
    private boolean uniqueConnectTimeConfig = true;
    private boolean uniqueConnectNumConfig = true;
    private RestClientBuilder builder;
    private RestHighLevelClient client;

    @Bean
    public RestHighLevelClient client() {
        builder = RestClient.builder(httpHost);
        if (uniqueConnectTimeConfig) {
            setConnectTimeOutConfig();
        }
        if (uniqueConnectNumConfig) {
            setMutiConnectConfig();
        }
        client=new RestHighLevelClient(builder);
        return client;
    }

    // 主要关于异步httpclient的连接延时配置
    public void setConnectTimeOutConfig() {
        builder.setRequestConfigCallback(new RequestConfigCallback() {

            @Override
            public Builder customizeRequestConfig(Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                requestConfigBuilder.setSocketTimeout(socketTimeOut);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return requestConfigBuilder;
            }
        });
    }

    // 主要关于异步httpclient的连接数配置
    public void setMutiConnectConfig() {
        builder.setHttpClientConfigCallback(new HttpClientConfigCallback() {

            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.setMaxConnTotal(maxConnectNum);
                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpClientBuilder;
            }
        });
    }

    public void close() {
        if (client != null) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}