package com.louisgeek.classichu.classic;

/**
 * Created by louisgeek on 2016/12/4.
 */

public interface ClassicModel<D> {
    void loadDataBase(String url, int pageNum, int pageSize, String queryKey, ClassicCallBack<D> baseCallBack);
}