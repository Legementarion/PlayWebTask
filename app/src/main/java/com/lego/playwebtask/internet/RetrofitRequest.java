package com.lego.playwebtask.internet;

import android.util.Log;

import com.lego.playwebtask.model.DataContent;
import com.lego.playwebtask.realm.database.NewsData;

import io.realm.Realm;
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
    private static Realm mRealm;
    private static final String BASE_URL = "http://www.cbc.ca/";

    private RetrofitRequest() {
        create();
    }

    public static RetrofitRequest getInstance(Realm realm) {
        mRealm = realm;
        return instance == null ? (instance = new RetrofitRequest()) : instance;
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



    public void getData() {
        Call<DataContent> call = api.getData();
        call.enqueue(new Callback<DataContent>() {
            @Override
            public void onResponse(Call<DataContent> call, final Response<DataContent> response) {
                if (response.code() == 200) {
                    Log.d("DATABASE", "execute: "+response.body().getmChannel().getItems().get(0).getMtitle());
//                    for (int i = 0; i<response.body().getItem().size(); i++) {
//                        final NewsData newsData = new NewsData();
//                        newsData.setTitle(response.body().getmChannel().getItems().get(i).getTitle());
//                        newsData.setDate(response.body().getmChannel().getItems().get(i).getDate());
//                        newsData.setAuthor(response.body().getmChannel().getItems().get(i).getAuthor());
//                        newsData.setLink(response.body().getItem().get(i).getLink());
//                        newsData.setUrl(response.body().getItem().get(i).getDescription());
//                        mRealm.executeTransaction(new Realm.Transaction() {
//                            @Override
//                            public void execute(Realm realm) {
//                                realm.copyToRealmOrUpdate(newsData);
//                            }
//                        });
//                    }
//                    mRealm.executeTransaction(new Realm.Transaction() {
//                        @Override
//                        public void execute(Realm realm) {
//                            Log.d("DATABASE", "execute: "+realm.where(NewsData.class).findAll().size());
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<DataContent> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }
}
