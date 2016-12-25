package com.hunterliy.hunternews.cache;

import com.hunterliy.hunternews.bean.JsonBean;

import rx.Observable;

public abstract class NetWorkCache<T extends JsonBean> {

    public abstract Observable<T> get(String key,Class<T> clz);
}
