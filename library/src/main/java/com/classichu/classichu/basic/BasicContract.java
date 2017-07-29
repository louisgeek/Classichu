package com.classichu.classichu.basic;

/**
 * Created by louisgeek on 2016/12/4.
 */

public interface BasicContract {

    interface View<D> extends BasicView {
        void setupData(D d);
        void setupMoreData(D d);
    }

    interface Presenter extends BasicPresenter {
        void gainCountData(int pageCount);
        void gainMoreData(int pageNum);
    }

}