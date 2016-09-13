package com.lego.playwebtask.request;

import com.lego.playwebtask.model.DataContent;

import retrofit2.Call;
import retrofit2.http.GET;


interface MediaApi {
    @GET("/cmlink/rss-topstories")
    Call<DataContent> getData();
}
