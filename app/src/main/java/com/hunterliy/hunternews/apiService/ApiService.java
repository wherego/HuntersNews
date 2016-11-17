package com.hunterliy.hunternews.apiService;

import com.hunterliy.hunternews.bean.NewsList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {
    @GET("social/")
    Observable<NewsList> getNews(@Query("key") String key, @Query("num") String num, @Query("page") int page);
}
