package com.example.helloandroid.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class AnalysisUtil {

    Gson gson = new Gson();

    public static String AnalysisThrowable(Throwable e) {
        if (!(e instanceof HttpException)) return null;
        HttpException ex = (HttpException) e;
        ResponseBody body = ex.response().errorBody();
        try{
            String errorString = body.string();
            JSONObject object = JSONObject.parseObject(errorString);
            String message = object.getString("Message");
            return message;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }
}
