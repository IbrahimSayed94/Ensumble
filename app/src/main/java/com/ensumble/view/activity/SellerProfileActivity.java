package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.SellerDetailsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SellerProfileActivity extends AppCompatActivity {



    int sellerId=0;


    //progress
    CustomDialogProgress progress;
    Handler handler;


    @BindView(R.id.seller_image)
    ImageView seller_image;

    @BindView(R.id.seller_name)
    TextView seller_name;

    @BindView(R.id.seller_rate)
    RatingBar seller_rate;

    @BindView(R.id.seller_category)
    TextView seller_category;
    @BindView(R.id.seller_countRate)
    TextView seller_countRate;
    @BindView(R.id.seller_mobile)
    TextView seler_mobile;
    @BindView(R.id.seller_address)
    TextView seller_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_profile);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);
        toolBar.setTitle(getString(R.string.sellerProfile));
        toolBar.setCartCount(PrefUser.getCartCount(getApplicationContext()));

        getId();
        getSellerDetails();

    } // function of onCreate


    private void getId()
    {
        if(getIntent() != null)
        {
            sellerId = getIntent().getIntExtra("id",0);
        }
    } // funciton of getId

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


    private void setSellerData(SellerDetailsResponse object)
    {
        seller_name.setText(object.getUser().getName());
        seller_rate.setRating(object.getUser().getRate());

        Glide.with(this)
                .load(Constant.USER_IMAGE_URL+object.getUser().getImg())
                .placeholder(R.drawable.placeholder)
                .into(seller_image);

        seller_address.setText("");
        seller_category.setText("");
        seler_mobile.setText(object.getUser().getPhone()+"");
        seller_countRate.setText("");

    } // function of setSellerData

    private void getSellerDetails()
    {

        progress = new CustomDialogProgress();
        progress.init(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.post(Constant.BASE_URL+"CampanyProfile")
                .addBodyParameter("user_id", String.valueOf(sellerId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SellerDetailsResponse.class, new ParsedRequestListener<SellerDetailsResponse>() {

                    @Override
                    public void onResponse(SellerDetailsResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            setSellerData(response);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of getSellerDetails
} // class of SellerProfileActivity
