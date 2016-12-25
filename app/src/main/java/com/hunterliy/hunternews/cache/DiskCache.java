package com.hunterliy.hunternews.cache;

import com.hunterliy.hunternews.bean.JsonBean;

import rx.Observable;

public class DiskCache implements ICache{


    @Override
    public <T extends JsonBean> Observable<T> get(String key, Class<T> clz) {
        return null;
    }

    @Override
    public <T extends JsonBean> void put(String key, T t) {

    }
}
