package com.ensumble.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.CategoryResponse;
import com.ensumble.Model.CategoryResponse;
import com.ensumble.R;
import com.ensumble.adapter.CategoryAdapter;
import com.ensumble.adapter.SellerCategoryAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesFragment extends Fragment
{
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        getCategories();

        return view;
    } // function of onCreateView

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    } // function of onAattach

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    } // function of onCreate


    private void initCategoriesList(List<CategoryResponse.DataBean> categoryList)
    {
        CategoryAdapter adapter=new CategoryAdapter(context,categoryList,"category");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // fnction of initCategoriesList

    private void getCategories()
    {
        progress = new CustomDialogProgress();
        progress.init(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.get(Constant.BASE_URL+"Categories")
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
                        }
                        else
                        {
                            Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of getCategories


} // class of CategoryiesFragment
