package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.ProductDetailsResponse;
import com.ensumble.Model.ProductReviewsResponse;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.adapter.ReviewsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductReviewsActivity extends AppCompatActivity {



    //progress
    CustomDialogProgress progress;
    Handler handler;

    int productId=0;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.average_rating)
    TextView average_rating;
    @BindView(R.id.total_rating_count)
    TextView total_rating_count;

    @BindView(R.id.rating_progress_5)
    ProgressBar rating_progress_5;
    @BindView(R.id.rating_progress_4)
    ProgressBar rating_progress_4;
    @BindView(R.id.rating_progress_3)
    ProgressBar rating_progress_3;
    @BindView(R.id.rating_progress_2)
    ProgressBar rating_progress_2;
    @BindView(R.id.rating_progress_1)
    ProgressBar rating_progress_1;

    int totalFive=0 , totalFour=0 , totalThree=0 , totalTwo=0 , totalOne=0;
    float rateValue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_reviews);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);
        toolBar.setTitle(getString(R.string.productRate));
        toolBar.setCartCount(PrefUser.getCartCount(getApplicationContext()));


        getData();
        getReviews();
    } // function of onCreate


    private void getData()
    {
        if(getIntent() != null)
        {
            productId = getIntent().getIntExtra("id",0);
        }
    } // funciton of getData

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


    private void setReviewList(List<ProductReviewsResponse.MessageBean> reviewList)
    {
        ReviewsAdapter adapter = new ReviewsAdapter(getApplicationContext(),reviewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    } // function of setReviewList

    private void setRateAndReviewData(ProductReviewsResponse object)
    {
         average_rating.setText(object.getRate()+"");
         total_rating_count.setText(object.getCount()+"");


        for(int i=0;i<object.getMessage().size();i++)
        {
            rateValue = object.getMessage().get(i).getValue();

            if(rateValue >= 1 && rateValue <= 1.5)
                totalOne++;
            if(rateValue > 1.5 && rateValue <= 2.5)
                totalTwo++;
            if(rateValue > 2.5 && rateValue <= 3.5)
                totalThree++;
            if(rateValue > 3.5 && rateValue <= 4.5)
                totalFour++;
            if(rateValue > 4.5 && rateValue <= 5)
                totalFive++;
        }


        Log.e("QP",""+totalOne+" : "+totalTwo+" : "+totalThree+" : "+totalFour+" : "+totalFive);
        rating_progress_1.setProgress(totalOne);
        rating_progress_2.setProgress(totalTwo);
        rating_progress_3.setProgress(totalThree);
        rating_progress_4.setProgress(totalFour);
        rating_progress_5.setProgress(totalFive);

    } // function of setRateAndReviewData

    private void getReviews()
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
        AndroidNetworking.post(Constant.BASE_URL + "GetRateProduct")
                .addBodyParameter("product_id", String.valueOf(productId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProductReviewsResponse.class, new ParsedRequestListener<ProductReviewsResponse>() {

                    @Override
                    public void onResponse(ProductReviewsResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {


                            if(response.getMessage().size() > 0)
                                setReviewList(response.getMessage());

                            setRateAndReviewData(response);

                        }
                        else if(response.getCode().equals("300"))
                        {
                          Toast.makeText(getApplicationContext(),getString(R.string.noRatingFounds),Toast.LENGTH_LONG).show();
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
    } // function of getReviews

    @OnClick(R.id.rate_product)
    public void rateProductButton()
    {

        Intent intent = new Intent(getApplicationContext(),EnterRateActivity.class);
        intent.putExtra("type","product");
        intent.putExtra("id",productId);
        startActivity(intent);
    } // function of rateProductButton
} // class of ProductReviewsActivity
