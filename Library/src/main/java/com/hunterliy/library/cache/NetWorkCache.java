package com.hunterliy.library.cache;

import com.hunterliy.library.bean.JsonBean;

import rx.Observable;

public abstract class NetWorkCache<T extends JsonBean> {

    public abstract Observable<T> get(String key, Class<T> clz);
}
