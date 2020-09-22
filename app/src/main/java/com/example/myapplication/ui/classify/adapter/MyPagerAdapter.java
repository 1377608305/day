package com.example.myapplication.ui.classify.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list;
    List<String> strings;
    public MyPagerAdapter(@NonNull FragmentManager fm, List<Fragment> mFragmentsList, List<String> mTitlesList) {
        super(fm);
        this.list=mFragmentsList;
        this.strings=mTitlesList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
