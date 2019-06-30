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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.adapter.ProductsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteFragment extends Fragment {

    Context context;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //progress
    CustomDialogProgress progress;
    Handler handler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_favorite, container, false);
        ButterKnife.bind(this, view);


        getMyFavorite();
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


    private void initProductList(List<ProductsResponse.ProductsBean> productList)
    {
        ProductsAdapter adapter=new ProductsAdapter(getContext(),productList,"favorite");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // function of initProductList

    private void getMyFavorite()
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
        AndroidNetworking.post(Constant.BASE_URL+"MyFavorite")
                .addBodyParameter("user_id", PrefUser.getUserId(getContext()))
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
                        }
                        else
                        {

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of getMyFavorite

} // class of MyFavoriteFragment