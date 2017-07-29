package com.louisgeek.classichu.patient;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.classichu.classichu.basic.BasicFragment;
import com.classichu.classichu.basic.helper.VectorOrImageResHelper;
import com.classichu.classichu.classic.ClassicActivity;
import com.classichu.titlebar.widget.ClassicTitleBar;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.patient.factory.PatientAtyFragmentFactory;

import butterknife.BindView;


public class PatientActivity extends ClassicActivity {

    @BindView(R.id.id_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.id_layout_content)
    LinearLayout id_layout_content;
    @BindView(R.id.id_navigation_view)
    NavigationView id_navigation_view;

    private MenuItem mMenuItem;
    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_patient;
    }

    @Override
    protected void initView() {
        setAppBarTitle("用户信息");

        //
        //StatusBarHelper.setColorForDrawerLayout(this,mDrawerLayout,true,0);
        //隐藏滚动条
        NavigationMenuView menuView  = (NavigationMenuView) id_navigation_view.getChildAt(0);
        menuView.setVerticalScrollBarEnabled(false);

        Drawable rightDrawable=
                VectorOrImageResHelper.getDrawable(R.drawable.ic_more_vert_black_24dp);
        mClassicTitleBar.setRightImage(rightDrawable);
        mClassicTitleBar.setOnTitleBarRightItemClickListener(new ClassicTitleBar.OnTitleBarRightItemClickListener() {
            @Override
            public void onRightClick(View view) {
                mDrawerLayout.openDrawer(Gravity.END);
            }
        });

        /**
         * 移除原有app bar， 放置到新的位置
         */
        ViewGroup contentViewRootLayout= (ViewGroup) getContentViewRootLayout();
        contentViewRootLayout.removeView(mClassicTitleBar);
        id_layout_content.addView(mClassicTitleBar,0);


        //显示第一个页面
        switchFragment(R.id.id_menu_item_patient_info);


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
                //切换
                switchFragment(item.getItemId());

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

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean configStatusBarColorEnable() {
        return false;
    }

    @Override
    protected AppBarStyle configAppBarStyle() {
        return AppBarStyle.ClassicTitleBar;
    }




    private BasicFragment mCurrentFragment;
    private void switchFragment(int itemId) {
        BasicFragment basicFragment = PatientAtyFragmentFactory.create(itemId);
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


}
