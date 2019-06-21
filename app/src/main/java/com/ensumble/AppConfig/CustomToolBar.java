package com.ensumble.AppConfig;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ensumble.R;

public class CustomToolBar {
    Toolbar toolbar;
    Activity activity;


    DrawerLayout drawer_layout;

    public CustomToolBar() {
    }

    public CustomToolBar(Activity activity) {
        this.activity = activity;

        initToolBar();

    } // constructor


    private void initToolBar() {
        toolbar = activity.findViewById(R.id.tool_bar);
        drawer_layout = activity.findViewById(R.id.drawer_layout);
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
} // class of CustomToolBar