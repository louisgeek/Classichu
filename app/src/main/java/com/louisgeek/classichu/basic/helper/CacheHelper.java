package com.louisgeek.classichu.basic.helper;

import android.graphics.Bitmap;

import com.louisgeek.classichu.basic.base.ACache;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by louisgeek on 2016/12/30.
 */

public class CacheHelper {
    public static <T> T getData(String key) {
        return (T) ACache.get(ContextHelper.getContext()).getAsObject(key);
    }

    public static void putData(String key, Serializable objectSerializable) {
        ACache.get(ContextHelper.getContext()).put(key, objectSerializable);
    }

    public static <T> void putDataList(String key, List<T> tList) {
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.addAll(tList);
        putData(key, arrayList);
    }

    public static String getString(String key) {
        return ACache.get(ContextHelper.getContext()).getAsString(key);
    }

    public static void putString(String key, String value) {
        ACache.get(ContextHelper.getContext()).put(key, value);
    }

    public static Bitmap getBitmap(String key) {
        return ACache.get(ContextHelper.getContext()).getAsBitmap(key);
    }

    public static void putBitmap(String key, Bitmap bitmap) {
        ACache.get(ContextHelper.getContext()).put(key, bitmap);
    }

    public static boolean remove(String key) {
        return ACache.get(ContextHelper.getContext()).remove(key);
    }

    public static File getCacheFile(String key) {
        return ACache.get(ContextHelper.getContext()).file(key);
    }

    public static void clearAllCache() {
        ACache.get(ContextHelper.getContext()).clear();
    }
}
