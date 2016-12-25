package com.hunterliy.hunternews.cache;

import com.hunterliy.hunternews.bean.JsonBean;

import rx.Observable;


public interface ICache {
    <T extends JsonBean> Observable<T> get(String key,Class<T> clz);
    <T extends JsonBean> void  put(String key,T t);

}
