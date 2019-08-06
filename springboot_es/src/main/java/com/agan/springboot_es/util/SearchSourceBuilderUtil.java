package com.agan.springboot_es.util;

import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * @Date: 2018/11/2
 * @Author: zhangxinhong
 * @Description:
 */
public class SearchSourceBuilderUtil {
    private SearchSourceBuilderUtil(){}

    public static SearchSourceBuilder getSearchSourceBuilder(Integer pageNo, Integer size, boolean explain, SortBuilder sort){
        SearchSourceBuilder builder = new SearchSourceBuilder();
        if(pageNo!=null && pageNo>0 && size !=null && size >= 0){
            builder.from((pageNo-1)*size);
        }
        builder.size(size);     //获取文档数据的数量   一般聚合统计时设置为0
        builder.explain(explain);   //是否按匹配度排序
        if(sort!=null){
            builder.sort(sort);  //排序
        }
        return builder;
    }
}
