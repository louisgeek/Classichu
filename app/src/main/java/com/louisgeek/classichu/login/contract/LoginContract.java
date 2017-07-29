package com.louisgeek.classichu.login.contract;

import com.classichu.classichu.basic.BasicContract;
import com.louisgeek.classichu.login.bean.AgencyBeanWrapper;
import com.louisgeek.classichu.login.bean.UserLoginBean;

/**
 * Created by Classichu on 2017-6-12.
 */

public interface LoginContract {
    interface View<D> extends BasicContract.View<D>{
        void toMainAty();
        void loginSuccess(UserLoginBean userLoginBean);
        void showAgencyDialog(AgencyBeanWrapper agencyBeanWrapper);
    }

    interface Presenter {
        void getAgency(String username);

        void doLogin(String username, String password,String jgid);

        void doLoginFromOtherApp();

        void doLoginWithBarcode();

        void doParseBarCode();

        void startUpdateService();

        void startMqttService();


    }
}
