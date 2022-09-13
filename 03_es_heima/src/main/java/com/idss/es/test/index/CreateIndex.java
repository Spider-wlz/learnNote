package com.idss.es.test.index;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author wlz
 * @date 2022/9/13 9:20
 */

public class CreateIndex {
    public static void main(String[] args) throws Exception{

        //创建客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );

        //创建索引
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
        //发送请求，获取响应
        CreateIndexResponse response = esClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
        //响应状态
        System.out.println("索引操作 = " + acknowledged);

        //关闭ES客户端
        esClient.close();
    }
}