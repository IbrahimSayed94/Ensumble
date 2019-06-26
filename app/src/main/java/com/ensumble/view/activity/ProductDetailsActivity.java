package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.R;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductDetailsActivity extends AppCompatActivity {


    int productId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);

        getData();

    } // function of onCreate


    private void getData()
    {
        if(getIntent() != null)
        {
            productId = getIntent().getIntExtra("id",0);
        }
    } // function of getData


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


  
} // function of ProductDetailsActivity
