package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.adapter.ProductsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductsFilteredByCategoryActivity extends AppCompatActivity {



    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    int subCategoryId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_filtered_by_category);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);
        toolBar.setTitle(getString(R.string.products));

        getId();

        getProducts();


    } // function of onCreate

    private void getId()
    {
        if(getIntent() != null)
        {
            subCategoryId = getIntent().getIntExtra("id",0);
        }
    } // funciton of getId

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts



    private void initProductList(List<ProductsResponse.ProductsBean> productList)
    {
        ProductsAdapter adapter=new ProductsAdapter(getApplicationContext(),productList,"home");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // function of initProductList



    private void getProducts()
    {

        Log.e("QP","categoryId : "+subCategoryId);
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
        AndroidNetworking.post(Constant.BASE_URL+"Products")
                .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .addBodyParameter("cat_id", String.valueOf(subCategoryId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProductsResponse.class, new ParsedRequestListener<ProductsResponse>() {

                    @Override
                    public void onResponse(ProductsResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            if(response.getProducts().size() > 0)
                                initProductList(response.getProducts());
                                 else
                            Toast.makeText(getApplicationContext(),getString(R.string.notFound),Toast.LENGTH_LONG).show();
                        }
                        else
                        {

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                }
                });
    } // function of getProducts
} // class of ProductsFilteredByCategoryActivity
