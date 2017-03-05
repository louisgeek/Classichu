package com.louisgeek.classichu.app;

import android.util.Log;


/**
 * Created by louisgeek on 2017/2/19.
 * 经典Log
 */

public class CLog {
    private static final String mTAG = "CLog";
    private static final boolean mEnable = true;

    public static void v(String msg) {
        if (mEnable){
            Log.v(mTAG,msg);
        }
    }
    public static void d(String msg) {
        if (mEnable){
            Log.d(mTAG,msg);
        }
    }
    public static void i(String msg) {
        if (mEnable){
            Log.i(mTAG,msg);
        }
    }
    public static void w(String msg) {
        if (mEnable){
            Log.w(mTAG,msg);
        }
    }
}
