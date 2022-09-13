package com.idss.es.test.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author wlz
 * @date 2022/9/13 10:25
 */

public class ConnectElasticsearch {

    public static void connect(ElasticsearchTask task){
        //创建客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http")));
        try {
            task.doSomeThing(esClient);
            //关闭客户端连接
            esClient.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}