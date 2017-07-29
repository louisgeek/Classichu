package com.classichu.classichu.classic;

import com.classichu.classichu.basic.BasicPresenter;
import com.classichu.classichu.basic.BasicView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by louisgeek on 2016/12/4.
 */

public abstract class ClassicPresenter<V extends BasicView> implements BasicPresenter {
    protected V mView;

    public ClassicPresenter(V view) {
        mView = view;
    }

    public void destroy() {
        mView = null;
        clearDisposable();
    }

    private CompositeDisposable mCompositeDisposable;

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    private void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}