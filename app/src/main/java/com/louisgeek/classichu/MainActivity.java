package com.louisgeek.classichu;

import com.classichu.classichu.classic.ClassicActivity;

public class MainActivity extends ClassicActivity {

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setAppBarTitle("dsada");
    }

    @Override
    protected boolean configSwipeBackEnable() {
        return false;
    }

    @Override
    protected boolean configBackBtnEnable() {
        return false;
    }

    @Override
    protected AppBarStyle configAppBarStyle() {
        return AppBarStyle.ClassicTitleBar;
    }

    @Override
    protected void initListener() {

    }
}
