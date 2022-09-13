package com.idss.es.test.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author wlz
 * @date 2022/9/13 9:13
 */

public class HelloElasticsearch {
    public static void main(String[] args) throws Exception{

        //创建客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        System.out.println("esClient = " + esClient);

        //关闭ES客户端
        esClient.close();
    }
}