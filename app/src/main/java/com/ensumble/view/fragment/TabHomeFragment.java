package com.ensumble.view.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.AdvertisingResponse;
import com.ensumble.Model.PagingProductsResponse;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.Paging.ViewModel.ProductViewModel;
import com.ensumble.Paging.adapter.ProductsPagingAdapter;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.adapter.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabHomeFragment extends Fragment
{


    int position=-1 , categoryId=0;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    //progress
    CustomDialogProgress progress;
    Handler handler;


    ProductViewModel viewModel;
    ProductsPagingAdapter adapter;

    public static Fragment getInstance(int position,int categoryId) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        bundle.putInt("id", categoryId);
        TabHomeFragment tabFragment = new TabHomeFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    } // function of getInstance

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        ButterKnife.bind(this, view);

        initProductList();

        //getProducts();
        return view;
    } // function of onCreateView

    private void getFragmentData()
    {
        position = getArguments().getInt("pos");
        categoryId = getArguments().getInt("id");

        initViewModel();
        getData();
    } // function of getFragmentData


    private void initViewModel()
    {
        viewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

    } // function of initViewModel

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentData();

    } // function of onCreate


    private void initProductList()
    {
        adapter=new ProductsPagingAdapter(getContext(),"home",getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // function of initProductList


    private void getData()
    {
        viewModel.userId.setValue(PrefUser.getUserId(getContext()));
        viewModel.categoryId = categoryId;
        viewModel.itemPagedList.observe(this, new Observer<PagedList<PagingProductsResponse.ProductsBean.DataBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<PagingProductsResponse.ProductsBean.DataBean> listBeans) {

                Log.e("QP","Fragment Product Size :  "+listBeans.size());
                adapter.submitList(listBeans);
            }
        });
    } // function of getData

    private void getProducts()
    {

        Log.e("QP","categoryId : "+categoryId);
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
        AndroidNetworking.post(Constant.BASE_URL+"Products")
                .addBodyParameter("user_id", PrefUser.getUserId(getContext()))
                .addBodyParameter("cat_id", String.valueOf(categoryId))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProductsResponse.class, new ParsedRequestListener<ProductsResponse>() {

                    @Override
                    public void onResponse(ProductsResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            //if(response.getProducts().size() > 0)
                          //initProductList(response.getProducts());
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
    } // function of getProducts
} // calss of TabHomeFragment
