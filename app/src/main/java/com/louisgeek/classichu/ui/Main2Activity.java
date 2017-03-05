package com.louisgeek.classichu.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.classichu.titlebar.helper.ClassicTitleBarMenuHelper;
import com.classichu.titlebar.widget.ClassicTitleBar;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.basic.tool.ToastTool;
import com.louisgeek.classichu.classic.ClassicMvpActivity;
import com.louisgeek.classichu.classic.ClassicPresenter;

public class Main2Activity extends ClassicMvpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main2);
    }

    @Override
    protected ClassicPresenter setupPresenter() {
        return null;
    }

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
       setAppBarTitle("页2");


       mClassicTitleBar.setRightImageClassicMore();
       mClassicTitleBar.setOnTitleBarRightItemClickListener(new ClassicTitleBar.OnTitleBarRightItemClickListener() {
            @Override
            public void onRightClick(View view) {
                //
                ClassicTitleBarMenuHelper.initMenu(view,R.menu.menu_classic);
                ClassicTitleBarMenuHelper.setOnMenuItemClickListener(new ClassicTitleBarMenuHelper.OnClassicTitleBarMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.id_menu_classic_1:
                                ToastTool.showShort("id_menu_classic_1");
                                break;
                            case R.id.id_menu_classic_2:
                                ToastTool.showShort("id_menu_classic_2");
                                break;
                        }
                        return false;
                    }
                });
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.id_fl_11
                ,BlankFragment.newInstance("","")).commit();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean configBackBtnEnable() {
        return true;
    }

    @Override
    protected AppBarStyle configAppBarStyle() {
        return AppBarStyle.ClassicTitleBar;
    }
}
