package com.hunterliy.douban;

import com.hunterliy.library.utils.AppClient;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public class DoubanApi {
        public interface Http {
            @GET("https://api.douban.com/v2/book/search")
            Observable<BookResponse> getBooks(@QueryMap Map<String, String> options);
        }
        public static Http http = AppClient.BaseRetrofit.create(Http.class);
}
