package com.louisgeek.classichu.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.classichu.classichu.basic.BasicFragment;
import com.classichu.classichu.basic.tool.CollectionTool;
import com.classichu.classichu.basic.widget.BottomNavigationViewSupport;
import com.classichu.classichu.classic.ClassicActivity;
import com.classichu.titlebar.widget.ClassicTitleBar;
import com.louisgeek.classichu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    protected void initView() {

        mClassicTitleBar.setCenterText("首页").setRightText("切换展示")
                //TODO ImageOrVectorResHelper
                .setOnTitleBarRightItemClickListener(new ClassicTitleBar.OnTitleBarRightItemClickListener() {
                    @Override
                    public void onRightClick(View view) {
                        if (mCurrentFragment instanceof MainFragment){
                            MainFragment mainFragment= (MainFragment) mCurrentFragment;
                            mainFragment.callAtAty_RightBtnClick();
                        }

                    }
                });


        initFragments();

        idBottomNavigationViewSupport.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_menu_item_1:
                        showFragment(0);
                        return true;
                    case R.id.id_menu_item_2:
                        showFragment(1);
                        return true;
                    case R.id.id_menu_item_3:
                        showFragment(2);
                        return true;
                    case R.id.id_menu_item_4:
                        showFragment(3);
                        return true;
                }
                return false;
            }
        });
        idBottomNavigationViewSupport.setClickedItem(R.id.id_menu_item_1);


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

    private List<BasicFragment> fragments = new ArrayList<>();

    public void initFragments() {
        fragments.add(MainFragment.newInstance("病人列表", "", 0));
        fragments.add(MainFragment.newInstance("联系人", "", 1));
        fragments.add(MainFragment.newInstance("动态", "", 2));
        fragments.add(MainFragment.newInstance("我的", "", 3));
    }

    private BasicFragment mCurrentFragment;

    private void showFragment(int position) {
        if (CollectionTool.isNotEmpty(fragments)
                && position >= 0 && position < fragments.size()) {
            //
            showFragment(fragments.get(position));
        }
    }

    /**
     * 控制 fragment 来回切换  的显示或隐藏
     *
     * @param toBasicFragment
     */
    private void showFragment(BasicFragment toBasicFragment) {
        if (toBasicFragment != mCurrentFragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            if (!toBasicFragment.isAdded()) {
                transaction.add(R.id.id_frame_layout_content, toBasicFragment);
            }
            //
            transaction.show(toBasicFragment).commitAllowingStateLoss();
            //
            mCurrentFragment = toBasicFragment;
        }
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
