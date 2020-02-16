package com.ensumble.Paging.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.ensumble.Model.PagingProductsResponse;
import com.ensumble.Paging.network.RestApi;
import com.ensumble.Paging.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<Integer, PagingProductsResponse.ProductsBean.DataBean> {

    private static final String TAG = "QP";

    private   String user_id ;
    private   int category_id ;
    private static final int FIRST_PAGE = 1;

    public ProductDataSource(String user_id, int category_id) {
        this.user_id = user_id;
        this.category_id = category_id;
    }

    public ProductDataSource() {
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, PagingProductsResponse.ProductsBean.DataBean> callback) {

        RestApi restApi = RetrofitClient.getClient().create(RestApi.class);
        Call<PagingProductsResponse> call = restApi.getProducts(user_id,category_id,FIRST_PAGE);
        call.enqueue(new Callback<PagingProductsResponse>() {
            @Override
            public void onResponse(Call<PagingProductsResponse> call, Response<PagingProductsResponse> response) {

                if(response.body() != null)
                {
                    callback.onResult(response.body().getProducts().getData(),null,FIRST_PAGE+1);
                }
                Log.e(TAG,"initial : "+FIRST_PAGE);

                Log.e("QP","ProductDataSource Product Size :  "+response.body().getProducts().getData().size());
            }

            @Override
            public void onFailure(Call<PagingProductsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PagingProductsResponse.ProductsBean.DataBean> callback) {

        RestApi restApi = RetrofitClient.getClient().create(RestApi.class);
        Call<PagingProductsResponse> call = restApi.getProducts(user_id,category_id,FIRST_PAGE);
        call.enqueue(new Callback<PagingProductsResponse>() {
            @Override
            public void onResponse(Call<PagingProductsResponse> call, Response<PagingProductsResponse> response) {

                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if(response.body() != null)
                {
                    callback.onResult(response.body().getProducts().getData(),adjacentKey);
                }
                Log.e(TAG,"before : "+params.key+" : "+FIRST_PAGE+" : "+adjacentKey);
            }

            @Override
            public void onFailure(Call<PagingProductsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, PagingProductsResponse.ProductsBean.DataBean> callback) {

        RestApi restApi = RetrofitClient.getClient().create(RestApi.class);
        Call<PagingProductsResponse> call = restApi.getProducts(user_id,category_id,FIRST_PAGE);
        call.enqueue(new Callback<PagingProductsResponse>() {
            @Override
            public void onResponse(Call<PagingProductsResponse> call, Response<PagingProductsResponse> response) {

                if(response.body() != null)
                {
                    callback.onResult(response.body().getProducts().getData(),params.key+1);
                }

                Log.e(TAG,"after : "+params.key+" : "+FIRST_PAGE);
            }

            @Override
            public void onFailure(Call<PagingProductsResponse> call, Throwable t) {

            }
        });
    }
}
