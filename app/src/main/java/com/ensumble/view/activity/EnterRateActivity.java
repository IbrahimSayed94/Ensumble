package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.BaseResponse;
import com.ensumble.Model.ProductReviewsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EnterRateActivity extends AppCompatActivity {

    @BindView(R.id.edit_message)
    EditText edit_message;

    @BindView(R.id.rating_bar)
    RatingBar ratingBar;

    float rate =0;
    String message;


    String type ="";
    int productId =0 , sellerId=0;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_rate);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);
        toolBar.setTitle(getString(R.string.enterRate));


        getData();
        initRatingBarClick();
    } //function of onCreate

    private void getData()
    {
        if(getIntent() != null)
        {
            type = getIntent().getStringExtra("type");

            if(type.equals("product"))
            {
                productId = getIntent().getIntExtra("id",0);
            }
            else if(type.equals("seller"))
            {
                sellerId = getIntent().getIntExtra("id",0);
            }
        }
    } // function of getData

    private  void initRatingBarClick()
    {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                rate = v ;

            }
        });
    } // function of inintRatingBarClick


    @OnClick(R.id.button_rate)
    public void rateButton()
    {
       message = edit_message.getText().toString();

       if (message.equals("") || rate == 0)
       {
           Toast.makeText(getApplicationContext(),getString(R.string.completeFields),Toast.LENGTH_LONG).show();
       }
       else
       {
          if(type.equals("product"))
              rateProduct();
          else if(type.equals("seller"))
              rateSeller();
       }
    } // function of rateButton


    private void rateProduct()
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
        AndroidNetworking.post(Constant.BASE_URL + "addRate")
                .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .addBodyParameter("value", String.valueOf(rate))
                .addBodyParameter("message", message)
                .addBodyParameter("product_id", String.valueOf(productId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {

                    @Override
                    public void onResponse(BaseResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {

                            Toast.makeText(getApplicationContext(),getString(R.string.successfullyDone),Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                    }
                });
    } // function of rateProduct

    private void rateSeller()
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
        AndroidNetworking.post(Constant.BASE_URL + "addRate")
                .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .addBodyParameter("value", String.valueOf(rate))
                .addBodyParameter("message", message)
                .addBodyParameter("company_id", String.valueOf(sellerId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {

                    @Override
                    public void onResponse(BaseResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {

                            Toast.makeText(getApplicationContext(),getString(R.string.successfullyDone),Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                    }
                });
    } // function of rateSeller

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


} // class of EnterRateActivity
