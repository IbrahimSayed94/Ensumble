package com.ensumble.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.util.Log;

import com.ensumble.view.fragment.TabHomeFragment;

import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter
{

    private List<String> categoryList ;
    private Context context;


    public HomeViewPagerAdapter(FragmentManager fm, List<String> categoryList, Context context) {
        super(fm);
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return TabHomeFragment.getInstance(i);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categoryList.get(position);
    }


} // class of CategoriesViewPagerAdapter

