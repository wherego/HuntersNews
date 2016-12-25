package com.hunterliy.hunternews.cache;

import com.hunterliy.hunternews.bean.JsonBean;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class CacheManager {

    private ICache memoryCache,diskCache;

    private CacheManager(){
        memoryCache = new MemoryCache();
        diskCache = new DiskCache();
    }

    public static final CacheManager getInstance(){
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final CacheManager INSTANCE = new CacheManager();
    }

    public <T extends JsonBean> Observable<T> load(String key,Class<T> clz,NetWorkCache<T> netWorkCache){
        Observable observable = Observable.concat(
                loadFromMemory(key,clz),
                loadFromDisk(key,clz),
                loadFromNet(key,clz,netWorkCache))
                .first(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(T t) {

                        return t!=null&&!t.isExpire();

                    }
                });
        return observable;
    }

    public <T extends JsonBean> Observable<T> loadFromMemory(String key,Class<T> clz){

        return memoryCache.get(key,clz);
    }

    public <T extends JsonBean> Observable<T> loadFromDisk(final String key,Class<T> clz){

        return diskCache.get(key,clz)
                .doOnNext(new Action1<T>(){

                    @Override
                    public void call(T t) {
                        if (null!=t)
                        memoryCache.put(key,t);
                    }
                });
    }

    public <T extends JsonBean> Observable<T> loadFromNet(final String key,Class<T> clz,NetWorkCache<T> netWorkCache){
        return netWorkCache.get(key,clz)
                .doOnNext(new Action1<T>() {
                    @Override
                    public void call(T t) {
                        if (null!=t){
                            memoryCache.put(key,t);
                            diskCache.put(key,t);
                        }

                    }
                });
    }
}
