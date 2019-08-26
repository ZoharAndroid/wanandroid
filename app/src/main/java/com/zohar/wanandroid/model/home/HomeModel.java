package com.zohar.wanandroid.model.home;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.ApiAddress;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.ToastUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/8 16:28
 * Describe:
 */
public class HomeModel implements IHomeModel{

    @Override
    public void sendHomeHttp(String url, final OnHttpListener httpListener) {
        OkHttpClient client = new OkHttpClient();

        LogUtils.d( "请求的地址为:" + url);

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                httpListener.httpFailed("网络请求失败！");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String result = response.body().string();
                Gson gson = new Gson();
                Article homeReuslt = gson.fromJson(result, Article.class);
                httpListener.httpSuccess(homeReuslt);
            }
        });
    }

}
