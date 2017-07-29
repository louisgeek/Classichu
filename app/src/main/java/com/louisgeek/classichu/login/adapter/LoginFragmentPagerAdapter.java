package com.louisgeek.classichu.login.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Pair;

import java.util.List;

/**
 * Created by Classichu on 2017-7-16.
 */

public class LoginFragmentPagerAdapter  extends FragmentPagerAdapter {
    private List<Pair<String,Fragment>> mFragmentPairList;



    public LoginFragmentPagerAdapter(FragmentManager fm,
                                     List<Pair<String, Fragment>> mFragmentPairList) {
        super(fm);
        this.mFragmentPairList = mFragmentPairList;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentPairList.get(position).second;
    }

    @Override
    public int getCount() {
        return mFragmentPairList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      //  return super.getPageTitle(position);
        return mFragmentPairList.get(position).first;
    }
}
