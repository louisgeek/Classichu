package com.louisgeek.classichu.main.contract;

import com.classichu.classichu.basic.BasicContract;

/**
 * Created by Classichu on 2017/5/27.
 */

public interface MainContract {
    interface Model<D> extends BasicContract.Model<D>{
    }

    interface View<D> extends BasicContract.View<D>{
    }

    interface Presenter extends  BasicContract.Presenter{
    }
}
