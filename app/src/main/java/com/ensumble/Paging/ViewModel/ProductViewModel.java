package com.ensumble.Paging.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.ensumble.Model.PagingProductsResponse;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.Paging.Repository.ProductFactory;

import io.reactivex.annotations.Nullable;

public class ProductViewModel extends ViewModel
{

    public LiveData<PagedList<PagingProductsResponse.ProductsBean.DataBean>> itemPagedList;
    public LiveData<PageKeyedDataSource<Integer, PagingProductsResponse.ProductsBean.DataBean>> liveDataSource;

    @Nullable
    public static MutableLiveData<String> userId = new MutableLiveData<>();
    @Nullable
    public static int categoryId ;


    public ProductViewModel() {

        ProductFactory itemDataSourceFactory = new ProductFactory(userId.getValue(),categoryId);
        liveDataSource = itemDataSourceFactory.getProductDataSource();
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(5).build();


        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();

    }
}
