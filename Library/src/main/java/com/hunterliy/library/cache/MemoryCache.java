package com.hunterliy.library.cache;

import android.util.Log;
import android.util.LruCache;

import com.google.gson.Gson;
import com.hunterliy.library.bean.JsonBean;

import java.io.UnsupportedEncodingException;

import rx.Observable;
import rx.Subscriber;

public class MemoryCache implements ICache {
    private LruCache<String,String> mLruCache;

    public MemoryCache(){
        int memorySize = ((int)Runtime.getRuntime().maxMemory())/8;
        mLruCache = new LruCache<String,String>(memorySize){
            @Override
            protected int sizeOf(String key, String value) {
                try {
                    return value.getBytes("utf-8").length;
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return value.getBytes().length;
            }
        };
    }
    @Override
    public <T extends JsonBean> Observable<T> get(final String key, final Class<T> clz) {
       return Observable.create(new Observable.OnSubscribe<T>() {
           @Override
           public void call(Subscriber<? super T> subscriber) {
               Log.e("cache", "记忆读取load from memory" + key);
               String result = mLruCache.get(key);
               if (subscriber.isUnsubscribed()){
                   return;
               }
               if(result ==null){
                   subscriber.onNext(null);
               }else {
                   T t = new Gson().fromJson(result, clz);
                   subscriber.onNext(t);
               }
               subscriber.onCompleted();
           }
       });
    }


    @Override
    public <T extends JsonBean> void put(String key, T t) {
        if (null!=t)
            Log.e("cache", "记忆存储save to memory: " + key);
        mLruCache.put(key,t.toString());
    }
}
