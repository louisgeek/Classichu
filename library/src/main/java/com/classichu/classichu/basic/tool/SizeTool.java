package com.classichu.classichu.basic.tool;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import static android.content.res.Resources.getSystem;

/**
 * Created by louisgeek on 2016/6/17.
 */
public class SizeTool {
    public static int dp2px(Context context, float dp) {
         float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    public static int dp2px_(Context context, float dp) {
        float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                context.getResources().getDisplayMetrics());
        return (int) value;
    }
    public static int dp2px(float dp) {
         float scale = getSystem().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    public static int dp2px_(float dp) {
         float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,
                 Resources.getSystem().getDisplayMetrics());
        return (int) value;
    }
}
