package com.zohar.wanandroid.model.login;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.http.cookies.CookieJarImpl;
import com.zohar.wanandroid.http.cookies.PersistentCookieStore;
import com.zohar.wanandroid.utils.SharePreferenceUtils;

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
    public void login(final Context context, String url, final String username, final String password, final OnLoginListener loginListener) {
        /**
         * https://www.wanandroid.com/user/login
         *
         * 方法：POST
         * 参数：
         * 	username，password
         */
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(new CookieJarImpl(new PersistentCookieStore(context))).build();
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
                Gson gson = new Gson();
                final RegisterData registerData = gson.fromJson(result, RegisterData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginListener.loginSuccess(registerData);
                    }
                });
                // 保存用户相关信息
                SharePreferenceUtils.saveUserInfo(context, username, password);
            }
        });
    }

    public void cancelTasks(){
        // TODO 终止线程池ThreadPool.shutDown()，AsyncTask.cancle()，或者调用框架的取消任务api
    }
}
