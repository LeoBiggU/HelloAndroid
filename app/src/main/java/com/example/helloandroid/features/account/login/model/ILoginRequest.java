package com.example.helloandroid.features.account.login.model;

import com.example.helloandroid.model.CookieBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILoginRequest {
    @POST("temp/login")
    Observable<CookieBean> Login(@Query("username") String username, @Query("password") String password, @Query("dbConName") String dbConName);
}
