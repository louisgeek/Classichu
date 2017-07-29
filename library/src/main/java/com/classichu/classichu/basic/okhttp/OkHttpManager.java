package com.classichu.classichu.basic.okhttp;


import com.classichu.classichu.app.CLog;
import com.classichu.classichu.app.ClassicApplication;
import com.classichu.classichu.basic.okhttp.Interceptor.HttpHeadersInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Classichu on 2017-6-5.
 */

public class OkHttpManager {

    /**
     * 双重校验锁 单例
     */
    private volatile static OkHttpManager instance;
    private OkHttpClient okHttpClient;
    private OkHttpManager() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                CLog.i(message);
            }
        });

        ClassicApplication classicApplication=ClassicApplication.getInstance();
        HttpHeadersInterceptor httpHeadersInterceptor=new HttpHeadersInterceptor(classicApplication.getHeadersMap());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //httrh requestInterceptor=new RequestInterceptor();
        //配置okhttp
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                //日志
                .addNetworkInterceptor(httpLoggingInterceptor)
               // .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(httpHeadersInterceptor)
                .build();
    }
    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
    public OkHttpClient newOkHttpClient() {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                CLog.i(message);
            }
        });
        ClassicApplication classicApplication=ClassicApplication.getInstance();
        HttpHeadersInterceptor httpHeadersInterceptor=new HttpHeadersInterceptor(classicApplication.getHeadersMap());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //httrh requestInterceptor=new RequestInterceptor();
        //配置okhttp
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(6 * 1000, TimeUnit.MILLISECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                //日志
              //  .addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor(httpLoggingInterceptor)
               // .addNetworkInterceptor(httpHeadersInterceptor)
               .addInterceptor(httpHeadersInterceptor)
                .build();
        return okHttpClient;
    }
}
