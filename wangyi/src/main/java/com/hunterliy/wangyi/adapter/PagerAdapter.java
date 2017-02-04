package com.hunterliy.wangyi.adapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hunterliy.wangyi.ui.Fragment_mobile;
import com.hunterliy.wangyi.ui.Fragment_nba;
import com.hunterliy.wangyi.ui.Fragment_startup;

public class PagerAdapter  extends FragmentPagerAdapter{

    private int numOfTab;
    public PagerAdapter(FragmentManager fm,int numOfTab) {
        super(fm);
        this.numOfTab = numOfTab;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment_mobile fragmentMobile = new Fragment_mobile();
                return fragmentMobile;
            case 1:
                Fragment_nba fragmentNba = new Fragment_nba();
                return fragmentNba;
            case 2:
                Fragment_startup fragmentStartup = new Fragment_startup();
                return fragmentStartup;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTab;
    }

}
