package com.zohar.wanandroid.model.register;

import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/29 10:53
 * Describe:
 */
public class RegisterModel implements IRegisterModel {

    private Handler mHandler = new Handler();

    @Override
    public void register(String url, String username, String password, String againPassword, final OnRegisterListener registerListener) {
        /**
         * https://www.wanandroid.com/user/register
         *
         * 方法：POST
         * 参数
         * 	username,password,repassword
         */
        OkHttpClient client = new OkHttpClient.Builder().build();
        LogUtils.d("post 提交的地址 ：" + url + " password : " + password);
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("repassword", againPassword).build();
        Request requestPost = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerListener.registerFailed("网络请求失败！");
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                LogUtils.d("返回来的数据：" + result);
                Gson gson = new Gson();
                final RegisterData registerData = gson.fromJson(result, RegisterData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerListener.registerSuccess(registerData);
                    }
                });

            }
        });
    }
}
