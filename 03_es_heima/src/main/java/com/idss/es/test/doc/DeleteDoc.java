package com.idss.es.test.doc;

import com.idss.es.test.util.ConnectElasticsearch;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;

/**
 * @author wlz
 * @date 2022/9/13 14:43
 */

public class DeleteDoc {
    public static void main(String[] args) {

        ConnectElasticsearch.connect(client -> {
            //创建请求对象
            DeleteRequest request = new DeleteRequest().index("user").id("1001");
            //客户端发送请求，获取响应对象
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            //打印信息
            System.out.println("response.toString() = " + response.toString());
        });
    }
}