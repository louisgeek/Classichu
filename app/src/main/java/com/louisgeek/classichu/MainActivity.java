package com.louisgeek.classichu;

import android.view.View;
import android.widget.FrameLayout;

import com.classichu.classichu.basic.tool.ToastTool;
import com.classichu.classichu.basic.widget.BottomNavigationViewSupport;
import com.classichu.classichu.classic.ClassicActivity;
import com.classichu.titlebar.widget.ClassicTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ClassicActivity {

    @BindView(R.id.id_frame_layout_content)
    FrameLayout idFrameLayoutContent;
    @BindView(R.id.id_bottom_navigation_view_support)
    BottomNavigationViewSupport idBottomNavigationViewSupport;

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setContentViewAfter() {
        ButterKnife.bind(this);
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




      /*  CLog.d("XXXX xzss:" + ScreenTool.getScreenDensity());
        int lonx = 900;

        CLog.d("XXXX COMPLEX_UNIT_DIP:" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, lonx, Resources.getSystem().getDisplayMetrics()));
        CLog.d("XXXX COMPLEX_UNIT_PX:" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, lonx, Resources.getSystem().getDisplayMetrics()));
        CLog.d("XXXX COMPLEX_UNIT_PT:" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, lonx, Resources.getSystem().getDisplayMetrics()));
        CLog.d("XXXX COMPLEX_UNIT_SP:" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, lonx, Resources.getSystem().getDisplayMetrics()));
        CLog.d("XXXX COMPLEX_UNIT_IN:" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_IN, lonx, Resources.getSystem().getDisplayMetrics()));
        CLog.d("XXXX COMPLEX_UNIT_MM:" + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, lonx, Resources.getSystem().getDisplayMetrics()));


        mClassicTitleBar.setOnTitleBarLeftItemClickListener(new ClassicTitleBar.OnTitleBarLeftItemClickListener() {
            @Override
            public void onLeftClick(View view) {
                ToastTool.showImageOk("111111");
            }
        });*/
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
