package com.m7amdelbana.android.trueCallerApp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.m7amdelbana.android.trueCallerApp.fragments.FormFragment;
import com.m7amdelbana.android.trueCallerApp.fragments.ListFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new ListFragment();
                return fragment;

            case 1:
                fragment = new FormFragment();
                return fragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Contacts";

            case 1:
                return "Add Contact";

            default:
                return null;

        }
    }
}