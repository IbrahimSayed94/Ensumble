package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.adapter.ProductsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchProductActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    @BindView(R.id.txt_noResults)
    TextView txt_noResults;

    @BindView(R.id.searchView)
    androidx.appcompat.widget.SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        ButterKnife.bind(this);



        initSearchView();
    } // function of onCreate

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("flag","homeFragment");
        startActivity(intent);
        finish();
    } // function of onBackPressed


    private void initSearchView()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getSearchResults(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    } // function of initSearchView


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts

    private void initProductList(List<ProductsResponse.ProductsBean> productList)
    {
        ProductsAdapter adapter=new ProductsAdapter(this,productList,"home");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // function of initProductList

    private void getSearchResults(String text)
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
        AndroidNetworking.post(Constant.BASE_URL+"search")
                .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .addBodyParameter("search", text)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProductsResponse.class, new ParsedRequestListener<ProductsResponse>() {

                    @Override
                    public void onResponse(ProductsResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            if(response.getProducts().size() > 0) {
                                txt_noResults.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                                initProductList(response.getProducts());
                            }else
                            {
                                txt_noResults.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
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
    } // function of getSearchResults

} // class of SearchProductActivity
