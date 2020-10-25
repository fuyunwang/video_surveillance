package com.fuyunwang.chuoyue.global;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @description: 由于网络I/O,所以部分数据以本地缓存替代Redis
 * @author: FuyunWang
 * @time: 2020/10/25 21:04
 */
public class CacheTools {

    private static final Cache<String,Object> commonCache;

    static {
        commonCache= CacheBuilder.newBuilder()
                .initialCapacity(10)
                .maximumSize(100)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .build();
    }

    private static class Holder{
        private static final CacheTools instance=new CacheTools();
    }

    public static CacheTools newInstance(){
        return Holder.instance;
    }

    public void setCommonCache(String key,Object value){
        commonCache.put(key,value);
    }

    public Object getFromCommonCache(String key){
        return commonCache.getIfPresent(key);
    }
}
