package com.zohar.wanandroid.model.login;

import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.User;
import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/7 15:22
 * Describe:
 */
public class LoginModel implements ILoginModel {

    private Handler mHandler = new Handler();

    @Override
    public void login(String url, final String username, final String password, final OnLoginListener loginListener) {
        /**
         * https://www.wanandroid.com/user/login
         *
         * 方法：POST
         * 参数：
         * 	username，password
         */
        OkHttpClient client = new OkHttpClient.Builder().build();
        LogUtils.d("post 提交的地址 ：" + url + " password : " + password);
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
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
                        loginListener.loginFailed("网络请求失败！");
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
                        loginListener.loginSuccess(registerData);
                    }
                });

            }
        });
    }

    public void cancelTasks(){
        // TODO 终止线程池ThreadPool.shutDown()，AsyncTask.cancle()，或者调用框架的取消任务api
    }
}
