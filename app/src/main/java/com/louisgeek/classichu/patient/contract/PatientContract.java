package com.louisgeek.classichu.patient.contract;

import com.classichu.classichu.basic.BasicContract;

/**
 * Created by Classichu on 2017/5/27.
 */

public interface PatientContract {


    interface View<D> extends BasicContract.View<D>{
    }

    interface Presenter extends  BasicContract.Presenter{
    }
}
