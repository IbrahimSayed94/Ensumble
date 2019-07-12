package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.ensumble.Model.SellerCategoriesResponse;
import com.ensumble.Model.SellersResponse;
import com.ensumble.R;
import com.ensumble.adapter.SellerCategoryAdapter;
import com.ensumble.adapter.SellersAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SellersActivity extends AppCompatActivity {



    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    int categoryId=0;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);

        getId();
        getSellers();

    } // function of onCreate


    private void getId()
    {
        if(getIntent() != null)
        {
            categoryId = getIntent().getIntExtra("id",0);
        }
    } // funciton of getId

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts

    private void initSellerList(List<SellersResponse.UsersBean> sellerList)
    {
        SellersAdapter adapter=new SellersAdapter(getApplicationContext(),sellerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // fnction of initSellerCategoryList

    private void getSellers()
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
        AndroidNetworking.post(Constant.BASE_URL+"CompanyOnCategory")
                .addBodyParameter("cat_id", String.valueOf(categoryId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SellersResponse.class, new ParsedRequestListener<SellersResponse>() {

                    @Override
                    public void onResponse(SellersResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            if(response.getUsers().size() > 0)
                                initSellerList(response.getUsers());
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
    } // function of getSellers
} // class of SellersActivity
