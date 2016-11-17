package com.hunterliy.hunternews.model;

import com.hunterliy.hunternews.bean.NewsListEntity;
import com.hunterliy.hunternews.utils.HttpUtils;

import java.util.List;

public class NewsListModel {

    private OnNewsListener newsListener;

    public interface OnNewsListener{
        void onSuccess(List<NewsListEntity> newsListEntityList);
        void onFailture();
    }

    public void getNewsData(OnNewsListener newsListener){
        this.newsListener = newsListener;
        final List<NewsListEntity> news_list = HttpUtils.getNewsData();
    }
}
