package com.agan.springboot_es.util;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.WildcardQueryBuilder;

/**
 * @Date: 2018/11/27
 * @Author: liuxuefeng
 * @Description:   生产查询条件
 */
public class QueryBuilerUtil {

    /**
     * 精确匹配查询
     *
     * @param field
     * @param value
     * @return
     */
    public static TermQueryBuilder getTermQuery(String field, int value) {
        return QueryBuilders.termQuery(field, value);
    }

    /**
     * 精确匹配查询
     *
     * @param field
     * @param value
     * @return
     */
    public static TermQueryBuilder getTermQuery(String field, String value) {
        return QueryBuilders.termQuery(field, value);
    }

    /**
     * 模糊查询   value支持 * ?
     *
     * @param field
     * @param value
     * @return
     */
    public static WildcardQueryBuilder getWildcardQueryBuilder(String field, String value) {
        return QueryBuilders.wildcardQuery(field, value);
    }

    /**
     * 数值范围查询
     *
     * @param field        字段
     * @param from         起
     * @param to           止
     * @param includeUpper 是否包括最大值(止)
     * @param includeLower 是否包括最小值(起)
     * @return
     */
    public static RangeQueryBuilder getNumRangeQuery(String field, int from, int to, boolean includeUpper, boolean includeLower) {
        return QueryBuilders.rangeQuery(field).from(from).to(to).includeUpper(includeUpper).includeLower(includeLower);
    }

    private QueryBuilerUtil() {
    }
}
