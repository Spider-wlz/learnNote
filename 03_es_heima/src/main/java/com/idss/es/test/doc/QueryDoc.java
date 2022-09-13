package com.idss.es.test.doc;

import com.idss.es.test.util.ConnectElasticsearch;
import com.idss.es.test.util.ElasticsearchTask;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.util.Map;

/**
 * @author wlz
 * @date 2022/9/13 15:59
 */

public class QueryDoc {

    /**
     * 聚合查询 aggregation- 查最大值
     */
    public static final ElasticsearchTask SEARCH_WITH_MAX = client -> {
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.aggregation(AggregationBuilders.max("maxAge").field("age"));
        //设置请求体
        request.source(sourceBuilder);
        //3.客户端发送请求，获取响应对象
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4.打印响应结果
        SearchHits hits = response.getHits();
        System.out.println("hits========>>");
        System.out.println("response = " + response);
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
    };

    /**
     * 聚合查询 aggregation- 分组查询
     */
    public static final ElasticsearchTask SEARCH_WITH_GROUP = client -> {
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(AggregationBuilders.terms("age_groupBy").field("age"));
        //设置请求体
        request.source(sourceBuilder);
        //3.客户端发送请求，获取响应对象
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4.打印响应结果
        SearchHits hits = response.getHits();
        System.out.println(response);
    };

    /**
     * 条件查询
     */
    public static final ElasticsearchTask SEARCH_BY_CONDITION = client -> {
        // 创建搜索请求对象
        SearchRequest request = new SearchRequest().indices("user");
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //1.全量查询
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        //2.条件查询
        sourceBuilder.query(QueryBuilders.termQuery("age", "30"));
        //5.模糊查询
        sourceBuilder.query(QueryBuilders.fuzzyQuery("name","wangwu").fuzziness(Fuzziness.ONE));
        //3.排序
        sourceBuilder.sort("age", SortOrder.ASC);
        //4.分页查询 当前页其实索引(第一条数据的顺序号):from 每页显示多少条:size
        sourceBuilder.from(0);
        sourceBuilder.size(2);


        request.source(sourceBuilder);
//组合查询 request.source(combinedQuery(sourceBuilder));
//范围查询 request.source(rangeQuery(sourceBuilder));
//高亮查询 request.source(highLighterQuery(sourceBuilder));

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());

            //高亮显示时输出
          /*  String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
            //打印高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields);*/
        }
        System.out.println("<<========");
    };

    public static void main(String[] args) {
//        ConnectElasticsearch.connect(SEARCH_BY_CONDITION);
        ConnectElasticsearch.connect(SEARCH_WITH_MAX);
    }

    /**
     * 组合查询 Bool- 请求体
     * Bool : must mustNot should
     * @return
     */
    static SearchSourceBuilder combinedQuery(SearchSourceBuilder sourceBuilder){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 必须包含
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", "30"));
        // 一定不含
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name", "zhangsan"));
        // 可能包含
        boolQueryBuilder.should(QueryBuilders.matchQuery("sex", "男"));
        sourceBuilder.query(boolQueryBuilder);
        return sourceBuilder;
    }

    /**
     * 范围查询 RangeQuery- 请求体
     * RangeQuery : gte（大于等于） lte(小于等于) should
     * @return
     */
    static SearchSourceBuilder rangeQuery(SearchSourceBuilder sourceBuilder){
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        rangeQuery.gte("30");
//        rangeQuery.lte("40");
        sourceBuilder.query(rangeQuery);
        return sourceBuilder;
    }

    /**
     * 高亮查询 TermsQueryBuilder - 请求体
     * RangeQuery : gte（大于等于） lte(小于等于) should
     * @return
     */
    static SearchSourceBuilder highLighterQuery(SearchSourceBuilder sourceBuilder){
        //构建查询方式：高亮查询
        TermsQueryBuilder termsQueryBuilder =
                QueryBuilders.termsQuery("name","zhangsan");
        //设置查询方式
        sourceBuilder.query(termsQueryBuilder);

        //构建高亮字段
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置标签前缀
        highlightBuilder.preTags("<font color='red'>");
        //设置标签后缀
        highlightBuilder.postTags("</font>");
        //设置高亮字段
        highlightBuilder.field("name");

        //设置高亮构建对象
        sourceBuilder.highlighter(highlightBuilder);
        return sourceBuilder;
    }


}