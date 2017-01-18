package com.hunterliy.wangyi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
