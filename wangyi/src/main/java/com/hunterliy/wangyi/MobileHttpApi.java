package com.hunterliy.wangyi;

import com.hunterliy.library.utils.AppClient;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public class MobileHttpApi {
    public interface Http {
        @GET("https://api.tianapi.com/mobile/")
        Observable<NewsResponse> getNewsData(@Query("key") String key, @Query("num") String num, @Query("page") int page);
    }

    public static  Http http = AppClient.BaseRetrofit.create(Http.class);
}
