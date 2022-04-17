package com.example.helloandroid.common.code.callback;

public interface ICallback {
    void onSuccess(Object returnObject);
    void onFailed(Throwable error);
}
