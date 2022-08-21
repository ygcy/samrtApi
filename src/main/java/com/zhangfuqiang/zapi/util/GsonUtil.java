package com.zhangfuqiang.zapi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author zhangfuqiang
 */
public class GsonUtil {

    private static Gson gson = new GsonBuilder().serializeNulls().create();


    public static <T> String toJson(T t){
        return gson.toJson(t);
    }
}
