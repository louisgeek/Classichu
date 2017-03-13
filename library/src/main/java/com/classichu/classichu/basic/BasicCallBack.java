package com.classichu.classichu.basic;

/**
 * Created by louisgeek on 2016/12/4.
 */

public interface BasicCallBack<D> {
    void onSuccess(D data);
    void onError(String msg);
}
