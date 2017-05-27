package com.louisgeek.classichu.api;


import com.louisgeek.classichu.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Classichu on 2017/5/22.
 */

public interface GitHubApi {
    @GET("/")
    Observable<BaseBean> listGitHubApis();
}
