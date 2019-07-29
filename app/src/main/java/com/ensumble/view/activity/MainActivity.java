package com.ensumble.view.activity;

import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.PefManager.PrefUser;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.R;
import com.ensumble.adapter.NavigationMenuAdapter;
import com.ensumble.view.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recycler_navigation_menu_items)
    RecyclerView recyclerView_navMenu;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.nav_view)
    NavigationView nav_view;

    String flag ="homeFragment";


    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_email)
    TextView user_email;


    @BindView(R.id.user_image)
    ImageView user_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);
        toolBar.setTitle(getString(R.string.home));
        toolBar.setCartCount(PrefUser.getCartCount(getApplicationContext()));

        setMenuHeader();

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


    private void setMenuHeader()
    {
        user_name.setText(PrefUser.getUsername(getApplicationContext()));
        user_email.setText(PrefUser.getEmail(getApplicationContext()));

        Glide.with(this)
            .load(Constant.USER_IMAGE_URL+PrefUser.getUserImage(getApplicationContext()))
            .placeholder(getResources().getDrawable(R.drawable.profile))
            .into(user_image);
    } // function of setMenuHeader

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


} // class of MainActivity
