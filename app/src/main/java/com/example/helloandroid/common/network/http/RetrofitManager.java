package com.example.helloandroid.common.network.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {

    // 唯一实例
    private static Retrofit instance;
    // 请求根路径
    public static final String BaseUrl = "http://192.168.31.38:8899/api/app/";

    private RetrofitManager(){ }

    public static Retrofit GetInstance(){

        if (instance == null) {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .build();

            instance = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return instance;
    }

}
