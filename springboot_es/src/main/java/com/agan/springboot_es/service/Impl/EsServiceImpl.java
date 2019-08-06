package com.agan.springboot_es.service.Impl;


import com.agan.springboot_es.bean.HelloEsBlog;
import com.agan.springboot_es.service.EsService;

import com.google.common.base.Preconditions;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.Persistence;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author cg
 */
@Slf4j
@Service
public class EsServiceImpl implements EsService {


    @Autowired
    private JestClient jestClient;
    @Value("${es.index}")
    private String index;
    @Value("${es.type}")
    private String type;

    @Override
    public void saveValue() {

        try{

            HelloEsBlog hb = new HelloEsBlog();
            int numid=(int)((Math.random()*9+1)*100000);
            hb.setId(numid);
            System.out.println("---"+numid);
            hb.setAuthor("agan");
            SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cl= Calendar.getInstance();
            String date=slf.format(cl.getTime());
            System.out.println(date);
            hb.setContext("blogs number:"+ numid);
            hb.setCreattime(slf.parse(date));
            hb.setStatus(1);

            Index firstIndex = new Index.Builder(hb).index(index).type(type).build();
            jestClient.execute(firstIndex);
        } catch (IOException io){

            io.getMessage();
            io.printStackTrace();
        } catch (ParseException pe){
            pe.getMessage();
            pe.printStackTrace();
        }

    }

    @Override
    public String getValue(String id) {


        Map<String,Object> searchParams = new HashMap<>();
        //and 关联
        //searchParams.put("id",id);
        searchParams.put("author","agan");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder filterQueryBuilders = QueryBuilders.boolQuery();
        for (Map.Entry<String,Object> entry : searchParams.entrySet()) {
            filterQueryBuilders.must(QueryBuilders.termQuery(entry.getKey(),entry.getValue()));
        }
        //Sort sort = new Sort("creattime",Sort.Sorting.DESC);
        searchSourceBuilder.postFilter(filterQueryBuilders);
       /* Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(index).addType(type).addSort(sort).build();*/
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex(index).addType(type).build();

        try {
            JestResult result = jestClient.execute(search);
           /* System.out.println(result.getJsonString());
            if (result != null && result.isSucceeded()){
                //return result.getSourceAsObjectList(clz);
            }*/
            System.out.println("=====================>result:{}."+result.getJsonString());
            return result.getJsonString();


        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String deleteValue(String id) {

        Delete delete = new Delete.Builder(id).index(index).type(type).build();
        JestResult result = null ;
        try {
            result = jestClient.execute(delete);
            log.info("deleteDocument == " + result.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.getJsonString();


    }

    public static void main(String[] args) {

        Preconditions.checkArgument(false,"hhhh");
        System.out.println();
    }



}
