package com.ensumble.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.AdvertisingResponse;
import com.ensumble.Model.HomeCategoriesResponse;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

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


    SharedPreferences sharedPreferences;

    TextSliderView textSliderView ;
    SliderLayout sliderShow ;


    //progress
    CustomDialogProgress progress;
    Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);


        getAdverisements(view);


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


    private void getCategories(View view)
    {
        progress = new CustomDialogProgress();
        progress.init(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.get(Constant.BASE_URL+"Categories")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(HomeCategoriesResponse.class, new ParsedRequestListener<HomeCategoriesResponse>() {

                    @Override
                    public void onResponse(HomeCategoriesResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            initCategoryTabs(view,response.getData());
                        }
                        else
                        {
                            Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of getCategories

    private void getAdverisements(View view)
    {
        progress = new CustomDialogProgress();
        progress.init(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.get(Constant.BASE_URL+"Advertising")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(AdvertisingResponse.class, new ParsedRequestListener<AdvertisingResponse>() {

                    @Override
                    public void onResponse(AdvertisingResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            imageSlider(view,response.getData());
                            getCategories(view);
                        }
                        else
                        {
                            Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of getCategory
    private  void  initCategoryTabs(View view,List<HomeCategoriesResponse.DataBean> categoryList)
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


    private   void  imageSlider (View view,List<AdvertisingResponse.DataBean> imageList)
    {

        sliderShow = view.findViewById(R.id.slider);

        for(int i = 0 ; i< imageList.size() ; i++) {
            textSliderView = new TextSliderView (getContext());
            textSliderView
                    .image(Constant.ADVERTISMENT_IMAGE_URL+imageList.get(i).getImg());
            sliderShow.addSlider(textSliderView);

        }

    } // function of imageSlider
} // class of homeFragment
