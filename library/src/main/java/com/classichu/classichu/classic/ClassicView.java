package com.classichu.classichu.classic;

/**
 * Created by louisgeek on 2016/12/4.
 */

public interface ClassicView<D> {
    void showProgress();

    void hideProgress();

    void showNoDataTip();

    void hideNoDataTip();

    void showMessage(String msg);

    void setupData(D data);

}