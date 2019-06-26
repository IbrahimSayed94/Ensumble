package com.ensumble.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.util.Log;

import com.ensumble.Model.HomeCategoriesResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.view.fragment.TabHomeFragment;

import java.util.List;

public class HomeViewPagerAdapter extends FragmentPagerAdapter
{

    private List<HomeCategoriesResponse.DataBean> categoryList ;
    private Context context;


    public HomeViewPagerAdapter(FragmentManager fm, List<HomeCategoriesResponse.DataBean> categoryList, Context context) {
        super(fm);
        this.categoryList = categoryList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        return TabHomeFragment.getInstance(i,categoryList.get(i).getId());
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(PrefUser.getLanguage(context).equals("ar"))
        return categoryList.get(position).getAr_title();
        else
            return categoryList.get(position).getEn_title();
    }


} // class of CategoriesViewPagerAdapter

