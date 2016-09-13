package com.lego.playwebtask.internet;

import com.lego.playwebtask.model.DataContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;


interface MediaApi {
    @GET("/cmlink/rss-topstories")
    Call<DataContent> getData();
}
