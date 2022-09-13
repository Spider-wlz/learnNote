package com.idss.es.test.index;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * @author wlz
 * @date 2022/9/13 10:08
 */

public class SearchIndex {
    public static void main(String[] args) throws Exception{

        //创建客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost",9200,"http"))
        );
        System.out.println("esClient = " + esClient);

        //查询索引
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);

        System.out.println("aliases" + response.getAliases());
        System.out.println("mapping" + response.getMappings());
        System.out.println("settings" + response.getSettings());

        //关闭ES客户端
        esClient.close();
    }
}