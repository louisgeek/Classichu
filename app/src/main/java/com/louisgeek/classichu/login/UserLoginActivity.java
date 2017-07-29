package com.louisgeek.classichu.login;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Pair;

import com.classichu.classichu.basic.tool.DeviceTool;
import com.classichu.classichu.classic.ClassicActivity;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.all.AppInfoDataManager;
import com.louisgeek.classichu.login.adapter.LoginFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserLoginActivity extends ClassicActivity {

    @Override
    protected int setupLayoutResId() {
        return R.layout.activity_user_login;
    }

    @Override
    protected boolean configSwipeBackEnable() {
        return false;
    }

    @Override
    protected void initView() {

        setAppBarTitle(R.string.appbar_title_login);
        AppInfoDataManager.getInstance().getAuthBean().DeviceName = DeviceTool.getDevice();
        AppInfoDataManager.getInstance().getAuthBean().ProductName = DeviceTool.getProduct();
        //todo authBean.MAC = WifiUtil.getMacAress(this);
        //todo authBean.IP = WifiUtil.getLocalIpAddress();
        /*authBean.Account = params[0];
        authBean.PWD = params[1];
        authBean.JGID = params[2];*/



        //  id_cil_username.getInputText().setInputType(InputType.TYPE_CLASS_NUMBER);
    /*    List<Pair<String,String>> sortList = new ArrayList<>();
        Pair<String,String> P1=new Pair<>("11","11111");
        Pair<String,String> P2=new Pair<>("22","22222");
        Pair<String,String> P3=new Pair<>("22","22222444");
        sortList.add(P1);
        sortList.add(P2);
        sortList.add(P3);
        String key="22";
        String value;
        for (Pair<String,String> pairKeyValue:sortList) {
            if (pairKeyValue.first.equals(key)){
                value=pairKeyValue.second;
                break;
            }
        }*/

        ViewPager viewPager=findById(R.id.id_view_pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.id_tab_layout);

        Fragment userLoginFragment=UserLoginFragment.newInstance("1","");
        Fragment userLoginFragment2=UserLoginFragment.newInstance("2","");
        List<Pair<String,Fragment>> fragmentPairList=new ArrayList<>();
        Pair<String, Fragment> pair=Pair.create("用户",userLoginFragment);
        Pair<String,Fragment> pair2=Pair.create("管理员",userLoginFragment2);
        fragmentPairList.add(pair);
        fragmentPairList.add(pair2);
        viewPager.setAdapter(new LoginFragmentPagerAdapter(getSupportFragmentManager(),
                fragmentPairList));

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected AppBarStyle configAppBarStyle() {
        return AppBarStyle.ClassicTitleBar;
    }

    @Override
    protected boolean configBackBtnEnable() {
        return false;
    }

    @Override
    protected void initListener() {

    }





}
