package com.ensumble.AppConfig;

import android.app.Activity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ensumble.R;

public class CustomToolBar {
    Toolbar toolbar;
    Activity activity;


    DrawerLayout drawer_layout;

    TextView  txt_toolbarTitle;

    public CustomToolBar() {
    }

    public CustomToolBar(Activity activity) {
        this.activity = activity;

        initToolBar();

    } // constructor


    private void initToolBar() {
        toolbar = activity.findViewById(R.id.tool_bar);
        drawer_layout = activity.findViewById(R.id.drawer_layout);
        txt_toolbarTitle = activity.findViewById(R.id.txt_toolbarTitle);
        ((AppCompatActivity) (activity)).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) (activity)).getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        if (((AppCompatActivity) (activity)).getSupportActionBar() != null) {
            ((AppCompatActivity) (activity)).getSupportActionBar().setDisplayShowTitleEnabled(false);

            if (drawer_layout != null) {
                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer_layout, toolbar, R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
                drawer_layout.addDrawerListener(toggle);
                toggle.syncState();
            } else {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.onBackPressed();
                    }
                });
            }
        }
    } // function of initToolBar

    public void setTitle(String title)
    {
        txt_toolbarTitle.setText(title);
    } // function of setTitle
} // class of CustomToolBar