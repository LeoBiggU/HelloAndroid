package com.example.helloandroid.features.account.login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.helloandroid.R;
import com.example.helloandroid.common.cache.ACache;
import com.example.helloandroid.databinding.ActivityLoginBinding;
import com.example.helloandroid.features.account.login.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private final Activity activity = LoginActivity.this;
    private final Context context = this;
    private ACache cache;

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);

        cache = ACache.get(activity);

        initObserver();

    }

    /**
     * 初始化自定义观察者
     */
    protected void initObserver(){
        viewModel.loginPrompt.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 登录按钮 点击事件
     */
    public void login(View view) {
        viewModel.login();
    }
}