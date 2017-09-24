package com.example.android.recyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import Fragments.CompleteCollectionFragment;
import Fragments.DuplicateCollectionFragment;
import Fragments.MyHousesFragment;
import Fragments.MissingCollectionFragment;


/**
 * Created by x085271 on 8/22/2017.
 */

public class TabsPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    public TabsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CompleteCollectionFragment();
            case 1:
                return new MissingCollectionFragment();
            case 2:
                return new DuplicateCollectionFragment();
            case 3:
                return new MyHousesFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
