package com.ensumble.Paging.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.ensumble.Model.PagingProductsResponse;
import com.ensumble.Model.ProductsResponse;

public class ProductFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, PagingProductsResponse.ProductsBean.DataBean>> productDataSource
            = new MutableLiveData<>();


    private   String user_id ;
    private   int category_id ;

    public ProductFactory(String user_id, int category_id) {
        this.user_id = user_id;
        this.category_id = category_id;
    }

    public ProductFactory() {
    }

    @Override
    public DataSource<Integer,PagingProductsResponse.ProductsBean.DataBean> create() {
        ProductDataSource itemDataSource = new ProductDataSource(user_id,category_id);
        productDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, PagingProductsResponse.ProductsBean.DataBean>> getProductDataSource() {
        return productDataSource;
    }
}
