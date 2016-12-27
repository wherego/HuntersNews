package com.hunterliy.hunternews.model;


import android.content.Loader;

import com.hunterliy.hunternews.bean.JsonBean;
import com.hunterliy.hunternews.bean.NewsList;
import com.hunterliy.hunternews.cache.NetWorkCache;

import rx.Observable;

public class INewsListModelImpl implements INewsListModel {

    private static final int PAGE_SIZE = 10;
    private static final String API = "http://api.tianapi.com/wxnew/?key=be7d7c5a9db7e299d8e537e058ea7cef&num=10";
    @Override
    public void loadNewsList(int pageNum, Loader.OnLoadCompleteListener<JsonBean> loader) {

//        String url = String.format(PAGE_SIZE,);
        NetWorkCache<NewsList> netWorkCache = new NetWorkCache() {
            @Override
            public Observable get(String key, Class clz) {



            }
        };

    }
}
