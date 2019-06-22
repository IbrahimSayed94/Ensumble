package com.ensumble.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ensumble.R;
import com.ensumble.adapter.HomeViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class HomeFragment extends Fragment
{

    Context context;
    TabLayout tabLayout;
    ViewPager viewPager;
    HomeViewPagerAdapter homeViewPagerAdapter;
    List<String> categoryList = new ArrayList<>();

    SharedPreferences sharedPreferences;

    TextSliderView textSliderView ;
    SliderLayout sliderShow ;
    List<String>  urls =new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        imageSlider(view);
        getCategory();
        initCategoryTabs(view);

        return view;
    } // function of onCreateView

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    } // function of onAattach

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    } // function of onCreate



    private void getCategory()
    {
        categoryList.add("Clothes");
        categoryList.add("Shoes");
        categoryList.add("Sports wear");
        categoryList.add("Accessories");
        categoryList.add("Watches");
    } // function of getCategory
    private  void  initCategoryTabs(View view)
    {
        viewPager =  view.findViewById(R.id.categories_viewpager);
        homeViewPagerAdapter = new HomeViewPagerAdapter(getFragmentManager() ,categoryList,getContext());
        viewPager.setAdapter(homeViewPagerAdapter);

        tabLayout =  view.findViewById(R.id.categories_tab);
        tabLayout.setupWithViewPager(viewPager);



        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("language", "ar").equals("ar")) {
            ViewCompat.setLayoutDirection(getActivity().getWindow().getDecorView(), ViewCompat.LAYOUT_DIRECTION_RTL);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ElMessiri-Regular.ttf");
            for (int i = 0, count = tabLayout.getTabCount(); i < count; i++) {
                TextView tv = (TextView)LayoutInflater.from(getContext()).inflate(R.layout.custom_tab,null);
                tv.setTypeface(font);
                tabLayout.getTabAt(i).setCustomView(tv);
            }

        }

        else if (sharedPreferences.getString("language", "ar").equals("en")) {
            ViewCompat.setLayoutDirection(getActivity().getWindow().getDecorView(), ViewCompat.LAYOUT_DIRECTION_LTR);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Regular.ttf");
            for (int i = 0, count = tabLayout.getTabCount(); i < count; i++) {
                TextView tv = (TextView)LayoutInflater.from(getContext()).inflate(R.layout.custom_tab,null);
                tv.setTypeface(font);
                tabLayout.getTabAt(i).setCustomView(tv);
            }
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getActivity().getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    } // function of initCategoryTabs


    private   void  imageSlider (View view)
    {
        if(urls.size() > 0)urls.clear();
        urls.add("http://www.discountdunia.com/oc-content/uploads/3/344.png");
        urls.add("http://www.u-mark.org/includes/templates/mens-clothes/images/summer-wear.jpg");
        urls.add("http://www.discountdunia.com/oc-content/uploads/31/2909.jpg");

        sliderShow = view.findViewById(R.id.slider);

        Log.i("QP"," images url : "+urls.size());

        for(int i = 0 ; i< urls.size() ; i++) {
            textSliderView = new TextSliderView (getContext());
            textSliderView
                    .image(urls.get(i));
            sliderShow.addSlider(textSliderView);
            Log.e("QP","Image : "+urls.get(i));
        }

    } // function of imageSlider
} // class of homeFragment
