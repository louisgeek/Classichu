package com.classichu.classichu.basic.http;


import com.classichu.classichu.basic.http.impls.OKHttpRequestManager;
import com.classichu.classichu.basic.http.impls.SystemHttpRequestManager;
import com.classichu.classichu.basic.http.interfaces.IHttpRequestManager;

/**
 * Created by louisgeek on 2016/12/28.
 */

public class HttpRequestManagerFactory {

    /**
     * 2016年12月28日13:52:01
     * 静态内部类实现单例模式方案的改写
     */
    private static class HttpRequestManagerFactoryInner4OKHttp {
        private static final OKHttpRequestManager INSTANCE = new OKHttpRequestManager();
    }

    /**
     * @return
     */
    public static IHttpRequestManager getRequestManager() {
        return HttpRequestManagerFactoryInner4OKHttp.INSTANCE;
        //   return HttpRequestManagerFactoryInner4SystemHttp.INSTANCE;
    }


    private static class HttpRequestManagerFactoryInner4SystemHttp {
        private static final SystemHttpRequestManager INSTANCE = new SystemHttpRequestManager();
    }
}
