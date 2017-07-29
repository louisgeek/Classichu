package com.louisgeek.classichu.main;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.classichu.classichu.basic.BasicFragment;
import com.classichu.classichu.basic.helper.VectorOrImageResHelper;
import com.classichu.classichu.basic.widget.BottomNavigationViewSupport;
import com.classichu.classichu.classic.ClassicActivity;
import com.classichu.titlebar.widget.ClassicTitleBar;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.main.factory.MainAtyFragmentFactory;

import butterknife.BindView;

public class MainActivity extends ClassicActivity {


    @BindView(R.id.id_bottom_navigation_view_support)
    BottomNavigationViewSupport idBottomNavigationViewSupport;
    @BindView(R.id.id_layout_content)
    LinearLayout id_layout_content;
    @BindView(R.id.id_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.id_navigation_view)
    NavigationView id_navigation_view;

    private MenuItem mMenuItem;
    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        Drawable leftDrawable=
                VectorOrImageResHelper.getDrawable(R.drawable.ic_menu_black_24dp);

        mClassicTitleBar.setCenterText("首页")
                .setLeftImage(leftDrawable)
                .setRightText("切换展示");
        mClassicTitleBar.setOnTitleBarLeftItemClickListener(new ClassicTitleBar.OnTitleBarLeftItemClickListener() {
            @Override
            public void onLeftClick(View view) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
        mClassicTitleBar.setOnTitleBarRightItemClickListener(new ClassicTitleBar.OnTitleBarRightItemClickListener() {
                    @Override
                    public void onRightClick(View view) {
                        Log.i("zfq", "onRightClick: click");
                        if (mCurrentFragment instanceof MainFragment) {
                            MainFragment mainFragment = (MainFragment) mCurrentFragment;
                            mainFragment.callAtAty_RightBtnClick();
                        }

                    }
                });
        /**
         * 移除原有app bar， 放置到新的位置
         */
        ViewGroup contentViewRootLayout= (ViewGroup) getContentViewRootLayout();
        contentViewRootLayout.removeView(mClassicTitleBar);
        id_layout_content.addView(mClassicTitleBar,0);


        idBottomNavigationViewSupport.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_menu_item_1:
                        switchFragment(0);
                        return true;
                    case R.id.id_menu_item_2:
                        switchFragment(1);
                        return true;
                    case R.id.id_menu_item_3:
                        switchFragment(2);
                        return true;
                    case R.id.id_menu_item_4:
                        switchFragment(3);
                        return true;
                }
                return false;
            }
        });
        idBottomNavigationViewSupport.setClickedItem(R.id.id_menu_item_1);
        //显示第一个页面
        switchFragment(0);
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
        //隐藏滚动条
        NavigationMenuView menuView  = (NavigationMenuView) id_navigation_view.getChildAt(0);
        menuView.setVerticalScrollBarEnabled(false);

        View headView=id_navigation_view.getHeaderView(0);
        //设置默认选中 选中的条件是该menu的checkable为true
        //id_navigation_view.setCheckedItem(R.id.id_menu_item_patient_list);
        //设置默认选中
        if (id_navigation_view.getMenu()!=null&&id_navigation_view.getMenu().size()>0){
            mMenuItem=id_navigation_view.getMenu().getItem(0);
            mMenuItem.setCheckable(true);
            //选中的条件是该menu的checkable为true
            id_navigation_view.setCheckedItem(mMenuItem.getItemId());
        }
        id_navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //同一个item直接关闭
                if (mMenuItem==item){
                    //关闭抽屉
                   mDrawerLayout.closeDrawers();
                    return false;
                }
                switch (item.getItemId()) {
                    case R.id.id_menu_item_patient_list:
                        break;
                    case R.id.id_menu_item_daily_work:
                        break;
                    case R.id.id_menu_item_check_drug:
                        break;
                    case R.id.id_menu_item_batch_signs:
                        break;
                    case R.id.id_menu_item_specimen_collection:
                        break;
                }
                //改变标题
                mClassicTitleBar.setCenterText(item.getTitle());
                //选中的条件是该menu的checkable为true
                item.setCheckable(true);
                //将选中设为文字选中的状态
                //item.setChecked(true);
                //
                //id_navigation_view.setCheckedItem(item.getItemId());
                //关闭抽屉
                mDrawerLayout.closeDrawers();
                //
                mMenuItem=item;
                return true;
            }
        });
    }


    private BasicFragment mCurrentFragment;
    private void switchFragment(int position) {
        BasicFragment basicFragment = MainAtyFragmentFactory.create(position);
        showFragment(basicFragment);
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
