package com.example.helloandroid.features.account.login.model;

import com.example.helloandroid.common.code.callback.ICallback;
import com.example.helloandroid.common.network.http.RetrofitManager;
import com.example.helloandroid.model.CookieBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginModel {

    public void login(String account, String password, ICallback callbackack){
        RetrofitManager.GetInstance()
                .create(ILoginRequest.class)
                .Login(account, password, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CookieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(CookieBean cookieBean) {
                        callbackack.onSuccess(cookieBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbackack.onFailed(e);
                    }

                    @Override
                    public void onComplete() { }
                });
    }

}
