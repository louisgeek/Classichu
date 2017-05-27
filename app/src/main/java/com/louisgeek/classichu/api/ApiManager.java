package com.louisgeek.classichu.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Classichu on 2017/5/22.
 */

public class ApiManager {
    /**
     * 双重校验锁 单例
     */
    //====
    private volatile static ApiManager instance;

    private ApiManager() {
        //配置okhttp
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager();
                }
            }
        }
        return instance;
    }

    //====
    private OkHttpClient okHttpClient;

    /**
     * github
     */
    private GitHubApi gitHubApi;
    public GitHubApi getGitHubApi() {
            if (gitHubApi == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .client(okHttpClient)
                        //string
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                gitHubApi = retrofit.create(GitHubApi.class);
            }
        return gitHubApi;
    }

    /**
     * douban
     */
    private DouBanApi douBanApi;
    public DouBanApi getDouBanApi() {
        if (douBanApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.douban.com/v2/")
                    .client(okHttpClient)
                    //string
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            douBanApi = retrofit.create(DouBanApi.class);
        }
        return douBanApi;
    }
    /**
     * test
     */
    private TestApi testApi;
    public TestApi getTestApi() {
        if (testApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://git.oschina.net/louisgeek/Demo/raw/master/api/")
                    .client(okHttpClient)
                    //string
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            testApi = retrofit.create(TestApi.class);
        }
        return testApi;
    }

}
