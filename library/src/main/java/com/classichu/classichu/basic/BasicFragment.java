package com.classichu.classichu.basic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Classichu on 2017/5/27.
 * <p>
 * // 根Fragment 一定要在save为null时才加载，Fragment中onCreateView等生命周里加载根子Fragment同理
 * // 因为在页面重启时，Fragment会被保存恢复，而此时再加载Fragment会重复加载，导致重叠
 * if(saveInstanceState == null){
 * // 正常情况下去 加载根Fragment
 * }
 */

public class BasicFragment extends Fragment {

    /**
     * 发生Fragment重叠的根本原因在于FragmentState没有保存Fragment的显示状态，即mHidden，
     * 导致页面重启后，该值为默认的false，即show状态，所以导致了Fragment的重叠。
     * <p>
     * 所以自己维护一个 mHidden
     */
    private static final String IS_SUPPORT_HIDDEN = "IS_SUPPORT_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(IS_SUPPORT_HIDDEN);
            //
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                fragmentTransaction.hide(this);
            } else {
                fragmentTransaction.show(this);
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //
        outState.putBoolean(IS_SUPPORT_HIDDEN, isHidden());
    }
}
