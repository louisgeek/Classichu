package com.louisgeek.classichu.ui;

import android.view.View;
import android.widget.TextView;

import com.classichu.titlebar.widget.ClassicTitleBar;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.basic.listener.OnNotFastClickListener;
import com.louisgeek.classichu.classic.ClassicActivity;

public class MainActivity extends ClassicActivity {
  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main;
    }
    TextView id_tv_1;
    ClassicTitleBar id_ctb;
    @Override
    protected void initView() {
        setAppBarTitle("主页");
        id_tv_1=findById(R.id.id_tv_1);

    }

    @Override
    protected boolean configSwipeBackEnable() {
       // return super.configSwipeBackEnable();
        return false;
    }

    @Override
    protected boolean configBackBtnEnable() {
       // return super.configBackBtnEnable();
        return false;
    }

    @Override
    protected AppBarStyle configAppBarStyle() {
        return AppBarStyle.ClassicTitleBar;
    }

    @Override
    protected void initListener() {
        //
        setOnNotFastClickListener(id_tv_1, new OnNotFastClickListener() {
            @Override
            protected void onNotFastClick(View v) {
                startAty(Main2Activity.class);
            }
        });

    }




}
