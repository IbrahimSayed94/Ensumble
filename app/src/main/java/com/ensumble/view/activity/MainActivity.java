package com.ensumble.view.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.R;
import com.ensumble.adapter.NavigationMenuAdapter;
import com.ensumble.view.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recycler_navigation_menu_items)
    RecyclerView recyclerView_navMenu;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    String flag ="homeFragment";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);

        setUpNavMenu();
        //getFlag();
        defaultFragment(flag);

    } // function of onCreate

    private void  getFlag()
    {
        if(getIntent() != null)
        {
            flag = getIntent().getStringExtra("flag");
            Log.e("QP","flag : "+flag);
        }
    } // function of getFlag
    private void defaultFragment(String flag) {
        if (flag.equals("homeFragment")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
    }// function defaultFragment
    private void setUpNavMenu()
    {
        NavigationMenuAdapter adapter = new NavigationMenuAdapter( getResources().getStringArray(R.array.navigation_menu_items),
                getResources().obtainTypedArray(R.array.nav_drawables_list),this,drawer_layout);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView_navMenu.setLayoutManager(layoutManager);
        recyclerView_navMenu.setHasFixedSize(true);
        recyclerView_navMenu.setAdapter(adapter);
    } // function of setUpNavMenu

} // class of MainActivity
