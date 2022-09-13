package com.idss.es.test.util;

import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author wlz
 * @date 2022/9/13 10:24
 */

public interface ElasticsearchTask {

    /**
     * 将连接ES客户端重构为lambda式
     * @param client
     * @throws Exception
     */
    void doSomeThing(RestHighLevelClient client) throws Exception;
}