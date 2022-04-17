package com.example.helloandroid.common.code.callback;

import com.example.helloandroid.common.util.AnalysisUtil;

public abstract class AbstractCallback {
    abstract void onSuccess(Object returnObject);
    public void onFailed(Throwable error){
        String message = AnalysisUtil.AnalysisThrowable(error);
    }
}
