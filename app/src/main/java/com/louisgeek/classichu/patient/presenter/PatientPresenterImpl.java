package com.louisgeek.classichu.patient.presenter;

import android.support.annotation.NonNull;

import com.classichu.adapter.recyclerview.ClassicRVHeaderFooterAdapter;
import com.classichu.classichu.app.CLog;
import com.classichu.classichu.basic.rxjava.RxHttpResultObserver;
import com.classichu.classichu.basic.rxjava.RxTransformerSchedulers;
import com.classichu.classichu.classic.ClassicPresenter;
import com.louisgeek.classichu.api.ApiServiceFactory;
import com.louisgeek.classichu.bean.BS_BaseBean;
import com.louisgeek.classichu.logic.douban.BookSearchBean;
import com.louisgeek.classichu.patient.bean.PatientInfoBean;
import com.louisgeek.classichu.patient.contract.PatientContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Classichu on 2017/5/27.
 */

public class PatientPresenterImpl extends ClassicPresenter<PatientContract.View>
        implements PatientContract.Presenter {


    public PatientPresenterImpl(PatientContract.View view) {
        super(view);
    }

    @Override
    public void gainData() {

    ApiServiceFactory.getInstance().getUserApi().getPatientInfoDetail("5","1")
            .compose(RxTransformerSchedulers.<BS_BaseBean<PatientInfoBean>>observable_io_main()) //compose配合进行线程调度
            .subscribe(new RxHttpResultObserver<BS_BaseBean<PatientInfoBean>>() {
                @Override
                protected void onStart(Disposable disposable) {
                    mView.showProgress();
                    //add 进行统一管理
                    addDisposable(disposable);
                }

                @Override
                public void onSuccess(BS_BaseBean<PatientInfoBean> patientInfoBeanBS_baseBean) {
                    if (patientInfoBeanBS_baseBean.getReType()==0){
                        mView.setupData(patientInfoBeanBS_baseBean.getData());
                    }else if (patientInfoBeanBS_baseBean.getReType()==100){
                        //未登录
                        onFailure(patientInfoBeanBS_baseBean.getMsg());
                    }else{
                        onFailure(patientInfoBeanBS_baseBean.getMsg());
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
    public void gainCountData(int pageCount) {
        ApiServiceFactory.getInstance().getDouBanApi().bookSearch("的",
                ClassicRVHeaderFooterAdapter.PAGE_SIZE_DEFAULT * ClassicRVHeaderFooterAdapter.PAGE_NUM_DEFAULT)
                .compose(RxTransformerSchedulers.<BookSearchBean>observable_io_main()) //compose配合进行线程调度
                /* .map(new Function<GitHubBean, GitHubBean>() {
                    @Override
                    public GitHubBean apply(@NonNull GitHubBean gitHubBean) throws Exception {
                        return gitHubBean;
                    }
                })*/
                .subscribe(new RxHttpResultObserver<BookSearchBean>() {
                    @Override
                    public void onStart(Disposable disposable) {
                        mView.showProgress();
                        //add 进行统一管理
                        addDisposable(disposable);
                    }

                    @Override
                    public void onSuccess(BookSearchBean bookSearchBean) {
                        mView.setupData(bookSearchBean);
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
               /* .subscribe(new Observer<GitHubBean>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GitHubBean gitHubBean) {
                        Log.i(TAG, "onNext: "+gitHubBean.getAuthorizations_url());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }

    public void gainMoreData(int pageNum) {

        ApiServiceFactory.getInstance().getDouBanApi().bookSearch("的",
                ClassicRVHeaderFooterAdapter.PAGE_SIZE_DEFAULT * pageNum)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /* .map(new Function<GitHubBean, GitHubBean>() {
                    @Override
                    public GitHubBean apply(@NonNull GitHubBean gitHubBean) throws Exception {
                        return gitHubBean;
                    }
                })*/
                .subscribeWith(new Observer<BookSearchBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        CLog.i("onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onNext(@NonNull BookSearchBean bookSearchBean) {
                        CLog.i("onNext: " + bookSearchBean.getBooks().get(0).getTitle());
                       mView.setupMoreData(bookSearchBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        CLog.i("onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        CLog.i("onComplete: ");
                    }
                });
               /* .subscribe(new Observer<GitHubBean>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GitHubBean gitHubBean) {
                        Log.i(TAG, "onNext: "+gitHubBean.getAuthorizations_url());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
    }


}
