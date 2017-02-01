package com.hunterliy.library.cache;

import rx.Observable;

public abstract class NetWorkCache<T extends JsonBean> {

    public abstract Observable<T> get(String key, Class<T> clz);
}
