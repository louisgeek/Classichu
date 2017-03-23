package com.classichu.classichu.basic.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by louisgeek on 2016/12/19.
 */

public class PreferenceManagerHelper {
    public final static String CONFIG_NOPIC_KEY = "checkbox_preference_nopic";

    public static boolean isNoPic(Context context) {
        Context appContext=context.getApplicationContext();
        SharedPreferences spConfigPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        return spConfigPreferences.getBoolean(CONFIG_NOPIC_KEY, true);
    }

    public static void setNoPic(SharedPreferences.Editor mEditor, boolean isNoPic) {
        mEditor.putBoolean(CONFIG_NOPIC_KEY, isNoPic);
        mEditor.commit();
    }

}
