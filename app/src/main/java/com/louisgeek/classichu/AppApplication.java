package com.louisgeek.classichu;

import com.classichu.classichu.app.ClassicApplication;
import com.classichu.classichu.basic.tool.ScreenTool;

/**
 * Created by louisgeek on 2017/3/8.
 */

public class AppApplication extends ClassicApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //
        setDebug(false);
        //
        if (isDebug()) {
            ScreenTool.getScreenDensity();
        }
    }
}
