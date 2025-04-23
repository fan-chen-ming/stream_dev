package com.cm.stream.realtime.v1.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * @Package com.cm.retail.v1.realtime.bean.DimJoinFunction
 * @Author chen.ming
 * @Date 2025/4/8 8:46
 * @description: DimJoinFunction
 */

public interface DimJoinFunction<T> {
    void addDims(T obj, JSONObject dimJsonObj) ;

    String getTableName() ;

    String getRowKey(T obj) ;
}
