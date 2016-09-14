package com.lego.playwebtask.request;

import android.util.Log;

import com.lego.playwebtask.model.DataContent;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class RetrofitRequest {

    private static RetrofitRequest instance;
    private MediaApi api;
    private static final String BASE_URL = "http://www.cbc.ca/";

    private RetrofitRequest() {
        create();
    }

    public static RetrofitRequest getInstance() {
        return instance == null ? (instance = new RetrofitRequest()) : instance;
    }

    private void create() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        api = retrofit.create(MediaApi.class);
    }


    public void getData(final RequestCallback callback) {
        Call<DataContent> call = api.getData();
        call.enqueue(new Callback<DataContent>() {
            @Override
            public void onResponse(Call<DataContent> call, final Response<DataContent> response) {
                if (response.code() == 200) {
                    callback.requestCallback(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<DataContent> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }
}
