package com.louisgeek.classichu.login.presenter;

import android.util.Log;

import com.classichu.classichu.basic.rxjava.RxHttpResultObserver;
import com.classichu.classichu.basic.rxjava.RxTransformerSchedulers;
import com.classichu.classichu.classic.ClassicPresenter;
import com.louisgeek.classichu.api.ApiServiceFactory;
import com.louisgeek.classichu.bean.BS_BaseBean;
import com.louisgeek.classichu.bean.BS_BaseListBean;
import com.louisgeek.classichu.login.bean.AgencyBean;
import com.louisgeek.classichu.login.bean.AgencyBeanWrapper;
import com.louisgeek.classichu.login.bean.UserLoginBean;
import com.louisgeek.classichu.login.contract.LoginContract;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Classichu on 2017-6-12.
 */

public class LoginPresenterImpl extends ClassicPresenter<LoginContract.View>
        implements LoginContract.Presenter {


    public LoginPresenterImpl(LoginContract.View view) {
        super(view);
    }

    @Override
    public void gainData() {

    }

    @Override
    public void getAgency(String username) {

        ApiServiceFactory.getInstance().getUserApi().getAgency(username)
                .compose(RxTransformerSchedulers.<BS_BaseListBean<AgencyBean>>observable_io_main()) //compose配合进行线程调度
                .subscribe(new RxHttpResultObserver<BS_BaseListBean<AgencyBean>>() {
                    @Override
                    protected void onStart(Disposable disposable) {
                        mView.showProgress();
                        //add 进行统一管理
                        addDisposable(disposable);
                    }

                    @Override
                    public void onSuccess(BS_BaseListBean<AgencyBean> agencyBS_baseListBean) {
                        Log.i("zfq", "onSuccess: ");
                        List<AgencyBean>  agencyBeanList=agencyBS_baseListBean.getData();
                        if (agencyBeanList!=null&&agencyBeanList.size()>0){
                            AgencyBeanWrapper agencyBeanWrapper=new AgencyBeanWrapper(agencyBeanList);
                            mView.showAgencyDialog(agencyBeanWrapper);
                        }else{
                            //机构数据size为0
                            onFailure("用户不存在");
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.showMessage(msg);
                    }

                    @Override
                    public void onFinish() {
                       mView.hideProgress();
                    }
                });
    }

    @Override
    public void doLogin(String username, String password,String jgid) {

        ApiServiceFactory.getInstance().getUserApi().userLogin(username,password,jgid)
        .compose(RxTransformerSchedulers.<BS_BaseBean<UserLoginBean>>observable_io_main()) //compose配合进行线程调度
        .subscribe(new RxHttpResultObserver<BS_BaseBean<UserLoginBean>>() {
            @Override
            protected void onStart(Disposable disposable) {
                mView.showProgress();
                //add 进行统一管理
                addDisposable(disposable);
            }

            @Override
            public void onSuccess(BS_BaseBean<UserLoginBean> userLoginBeanBS_baseBean) {
                mView.loginSuccess(userLoginBeanBS_baseBean.getData());
            }

            @Override
            public void onFailure(String msg) {
                mView.showMessage(msg);
            }

            @Override
            public void onFinish() {
                mView.hideProgress();
            }
        });
    }

    @Override
    public void doLoginFromOtherApp() {

    }

    @Override
    public void doLoginWithBarcode() {

    }

    @Override
    public void doParseBarCode() {

    }

    @Override
    public void startUpdateService() {

    }

    @Override
    public void startMqttService() {

    }
}
