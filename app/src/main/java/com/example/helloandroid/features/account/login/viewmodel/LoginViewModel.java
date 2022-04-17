package com.example.helloandroid.features.account.login.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.helloandroid.common.cache.ACache;
import com.example.helloandroid.common.code.callback.ICallback;
import com.example.helloandroid.common.util.AnalysisUtil;
import com.example.helloandroid.features.account.login.model.LoginModel;
import com.example.helloandroid.model.CookieBean;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class LoginViewModel extends AndroidViewModel {

    // 关联的Model
    private final LoginModel model;
    // 缓存
    private ACache cache;

    // 账号
    public MutableLiveData<String> account = new MutableLiveData<>();
    // 密码
    public MutableLiveData<String> password = new MutableLiveData<>();
    // 登录提醒
    public MutableLiveData<String> loginPrompt = new MutableLiveData<>();
    // 是否记住我
    public boolean rememberMe = false;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        model = new LoginModel();
        cache = ACache.get(application);
    }

    public void login() {

        if (isBlank(account.getValue()) || isBlank(password.getValue())){
            loginPrompt.postValue("用户名或密码不能为空");
            return;
        }

        model.login(account.getValue(), password.getValue(), new ICallback() {
            @Override
            public void onSuccess(Object returnObject) {
                CookieBean bean = (CookieBean) returnObject;
                String cookie = bean.m_name + "=" + bean.m_value + "; $Port";
                cache.put("cookie", cookie);
                cache.put("userCode", account.getValue());
                cache.put("password", password.getValue());
                cache.put("rememberMe", (rememberMe ? String.valueOf(rememberMe) : "false"));
                loginPrompt.postValue("登录成功");
            }

            @Override
            public void onFailed(Throwable error) {
                String message = AnalysisUtil.AnalysisThrowable(error);
                loginPrompt.postValue(message);
            }
        });
    }
}
