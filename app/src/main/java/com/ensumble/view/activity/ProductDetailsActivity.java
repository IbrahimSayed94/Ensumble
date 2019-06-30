package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.HomeCategoriesResponse;
import com.ensumble.Model.ProductDetailsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductDetailsActivity extends AppCompatActivity {


    int productId=0;

    //progress
    CustomDialogProgress progress;
    Handler handler;

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
            getProductDetails();
        }
    } // function of getData


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


  private void getProductDetails()
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
      AndroidNetworking.post(Constant.BASE_URL+"Product")
              .addBodyParameter("id", String.valueOf(productId))
              .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
              .setPriority(Priority.MEDIUM)
              .build()
              .getAsObject(ProductDetailsResponse.class, new ParsedRequestListener<ProductDetailsResponse>() {

                  @Override
                  public void onResponse(ProductDetailsResponse response) {
                      progress.dismiss();

                      if(response.getCode().equals("200"))
                      {

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
  } // get ProductDetails


} // function of ProductDetailsActivity
