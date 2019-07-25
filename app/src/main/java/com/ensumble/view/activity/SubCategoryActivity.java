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
import com.ensumble.Model.CategoryResponse;
import com.ensumble.R;
import com.ensumble.adapter.CategoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SubCategoryActivity extends AppCompatActivity {



    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    int subCategoryId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);
        toolBar.setTitle(getString(R.string.categories));


        getId();

        getCategories();

    } // function of onCreate

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts

    private void getId()
    {
        if(getIntent() != null)
        {
            subCategoryId = getIntent().getIntExtra("id",0);
        }
    } // funciton of getId


    private void initCategoriesList(List<CategoryResponse.DataBean> categoryList)
    {
        CategoryAdapter adapter=new CategoryAdapter(getApplicationContext(),categoryList,"subCategory");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // fnction of initCategoriesList

    private void getCategories()
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
        AndroidNetworking.post(Constant.BASE_URL+"SubCategories")
                .addBodyParameter("id", String.valueOf(subCategoryId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(CategoryResponse.class, new ParsedRequestListener<CategoryResponse>() {

                    @Override
                    public void onResponse(CategoryResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            if(response.getData().size() > 0)
                                initCategoriesList(response.getData());
                            else
                                Toast.makeText(getApplicationContext(),getString(R.string.notFound),Toast.LENGTH_LONG).show();
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
    } // function of getCategories
} // class of SubCategoryActivity
