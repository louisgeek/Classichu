package com.louisgeek.classichu.classic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.louisgeek.classichu.app.CLog;
import com.louisgeek.classichu.basic.data.FinalData;
import com.louisgeek.classichu.basic.event.BasicEvent;
import com.louisgeek.classichu.basic.listener.OnNotFastClickListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by louisgeek on 2016/10/31.
 */

public abstract class ClassicFragment extends Fragment {
    //
    protected static final String ARG_PARAM1 = "param1";
    protected static final String ARG_PARAM2 = "param2";
    protected static final String ARG_PARAM3 = "param3";

    protected String mParam1;
    protected String mParam2;
    protected int mParam3;

    protected static String mTag;
    protected Context mContext;
    protected View mRootLayout;

    /**
     * ===================================protected lifecycle=============================
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mTag = this.getClass().getSimpleName();

        mRootLayout = inflater.inflate(setupLayoutResId(), container, false);
        //
        EventBus.getDefault().register(this);

        /**
         * last
         */
        initView(mRootLayout);
        initListener();
        return mRootLayout;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * ===================================protected abstract=============================
     */
    protected abstract int setupLayoutResId();

    protected abstract void initView(View rootLayout);

    protected abstract void initListener();

    /**
     * =======================================protected===================================
     */
    protected <V extends View> V findById(int resId) {
        return (V) mRootLayout.findViewById(resId);
    }

    protected void startAty(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    protected void startAty(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected Bundle createBundleExtraInt1(int value1) {
        Bundle bundle = new Bundle();
        bundle.putInt(FinalData.BUNDLE_EXTRA_KEY_1, value1);
        return bundle;
    }

    protected Bundle createBundleExtraStr1(String value1) {
        Bundle bundle = new Bundle();
        bundle.putString(FinalData.BUNDLE_EXTRA_KEY_1, value1);
        return bundle;
    }
    /**
     * 防止重复点击的监听
     *
     * @param view
     * @param onNotFastClickListener
     */
    protected void setOnNotFastClickListener(final View view,
                                             final OnNotFastClickListener onNotFastClickListener) {
        if (view != null) {
            view.setOnClickListener(onNotFastClickListener);
        }
    }
    /**
     * =====================================public===========================
     */
    /**
     * 防止抛异常
     * EventBusException：its super classes have no public methods with the @Subscribe annotation
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThreadFragment(BasicEvent event) {
        CLog.d("onEventMainThreadFragment");
    }
}
