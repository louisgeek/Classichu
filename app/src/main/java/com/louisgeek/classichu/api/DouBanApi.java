package com.louisgeek.classichu.api;


import com.louisgeek.classichu.logic.douban.BookSearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Classichu on 2017/5/22.
 */

public interface DouBanApi {
    @GET("book/search")
    Observable<BookSearchBean> bookSearch(
            @Query("q") String key,@Query("start") int startOffset
    );
}
