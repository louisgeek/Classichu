package com.louisgeek.classichu.classic;

/**
 * Created by louisgeek on 2016/12/4.
 */

public interface ClassicPresenter<V> {
    void attachView(V view);

    void detachView();

    void gainCountData(int pageCount);

    void gainMoreData(int pageNum);
}