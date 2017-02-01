package com.hunterliy.library.cache;

import rx.Observable;


public interface ICache {
    <T extends JsonBean> Observable<T> get(String key, Class<T> clz);
    <T extends JsonBean> void  put(String key, T t);

}
