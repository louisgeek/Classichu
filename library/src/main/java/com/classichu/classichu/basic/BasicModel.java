package com.classichu.classichu.basic;

/**
 * Created by louisgeek on 2016/12/4.
 */
@Deprecated
public interface BasicModel<D> {
    void loadData(String url, int pageNum, int pageSize, String queryKey, BasicCallBack<D> baseCallBack);
}