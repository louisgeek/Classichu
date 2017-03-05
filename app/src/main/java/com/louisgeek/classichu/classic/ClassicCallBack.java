package com.louisgeek.classichu.classic;

/**
 * Created by louisgeek on 2016/12/4.
 */

public interface ClassicCallBack<D> {
    void onSuccess(D data);

    void onError(String msg);
}
