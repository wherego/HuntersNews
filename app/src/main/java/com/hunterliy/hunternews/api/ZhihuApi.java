package com.hunterliy.hunternews.api;

import com.hunterliy.hunternews.bean.NewsDetailResponse;
import com.hunterliy.hunternews.bean.StoryResponse;
import com.hunterliy.library.utils.AppClient;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public class ZhihuApi {
    public interface Http {
        @GET("news/latest")
        Observable<StoryResponse> getLatestNews();

        @GET("news/before/{path}")
        Observable<StoryResponse> getHistoryNews(@Path("path") String path);

        @GET("news/{key}")
        Observable<NewsDetailResponse> getNewsDetail(@Path("key") String key);
    }

    public static Http http = AppClient.BaseRetrofit.create(Http.class);
}
