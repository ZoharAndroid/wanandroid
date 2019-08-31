package com.zohar.wanandroid.model.collect;

import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.SharePreferenceUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/30 16:54
 * Describe:
 */
public class CollectModel implements  ICollectModel {

    private Handler mHandler = new Handler();

    @Override
    public void collect(String url, int articleId, final OnCollectListener onCollectListener) {
        // post提交
        OkHttpClient client = new OkHttpClient.Builder().build();
        LogUtils.d("Post :" + url);
        // todo : 请求添加cookies信息
        RequestBody requestBody = new FormBody.Builder().build();
        Request requestPost = new Request.Builder()
                .post(requestBody)
                .url(url)
                //.addHeader("Cookie","loginUserName" + )
                .build();

        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onCollectListener.collectFailed("网络异常！");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Article data = gson.fromJson(result, Article.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onCollectListener.collectSuccess(data);
                    }
                });
            }
        });
    }
}
