package com.louisgeek.classichu;

import android.view.View;

import com.classichu.classichu.basic.tool.ToastTool;
import com.classichu.classichu.classic.ClassicActivity;
import com.classichu.titlebar.widget.ClassicTitleBar;

public class MainActivity extends ClassicActivity {

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

      mClassicTitleBar.setCenterText("首页").setLeftText("1111").setRightText("2222")
                //TODO ImageOrVectorResHelper
                 .setOnTitleBarRightItemClickListener(new ClassicTitleBar.OnTitleBarRightItemClickListener() {
                    @Override
                    public void onRightClick(View view) {
                        ToastTool.showImageOk("222222");
                    }
                });
        mClassicTitleBar.setOnTitleBarLeftItemClickListener(new ClassicTitleBar.OnTitleBarLeftItemClickListener() {
            @Override
            public void onLeftClick(View view) {
                ToastTool.showImageOk("111111");
            }
        });
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

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return super.onCreateOptionsMenu(menu);
    }*/
}
