package com.ensumble.Paging.network;

import com.ensumble.Model.PagingProductsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi
{
    @FormUrlEncoded
    @POST("Products")
    Call<PagingProductsResponse> getProducts(
            @Field("user_id") String user_id,
            @Field("cat_id") int cat_id,
            @Query("page") int pageNumber
    );
} // interface of RestApi
