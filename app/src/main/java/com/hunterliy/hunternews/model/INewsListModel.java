package com.hunterliy.hunternews.model;

import android.content.Loader;

import com.hunterliy.hunternews.bean.JsonBean;

public interface INewsListModel {

    void loadNewsList(int pageNum,Loader.OnLoadCompleteListener<JsonBean> loader);
}
