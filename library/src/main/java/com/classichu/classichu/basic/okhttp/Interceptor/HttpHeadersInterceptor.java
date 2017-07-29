package com.classichu.classichu.basic.okhttp.Interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by louisgeek on 2017/6/5.
 */

public class HttpHeadersInterceptor implements Interceptor {
    public HttpHeadersInterceptor(Map<String, String> headMaps) {
        this.mHeadMaps = headMaps;
    }
    private Map<String,String> mHeadMaps;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder= request.newBuilder();
        //newBuilder.addHeader("Accept", "Application/JSON");
        for (String key:mHeadMaps.keySet()) {
            String value=mHeadMaps.get(key);
            newBuilder.addHeader(key,value);
        }
        Request newRequest=newBuilder.build();


        Response response = chain.proceed(newRequest);
        return response;
    }
}
