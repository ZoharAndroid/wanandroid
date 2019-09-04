package com.zohar.wanandroid.model.login;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.base.IBaseModel;
import com.zohar.wanandroid.bean.register.RegisterData;
import com.zohar.wanandroid.http.HttpRequestUtils;
import com.zohar.wanandroid.utils.SharePreferenceUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/30 8:57
 * Describe:
 */
public class LogoutModel implements ILogoutModel {

    private Handler mHandler = new Handler();

    @Override
    public void logout(final Context context, String url, final OnLogoutListener onLogoutListener) {
        // 发送请求
        Call call = HttpRequestUtils.sendHttpGetRequest(context, url);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onLogoutListener.logoutFailed("网络请求失败！");
                    }
                });

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final RegisterData logoutData = gson.fromJson(result, RegisterData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onLogoutListener.logoutSuccess(logoutData);
                    }
                });
                // 清除数据
                SharePreferenceUtils.clearUserInfo(context);
            }
        });
    }
}
