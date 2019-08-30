package com.zohar.wanandroid.model.search;

import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.home.Article;
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
 * Created by zohar on 2019/8/30 14:21
 * Describe:
 */
public class SearchResultModel implements ISearchResultModel {

    private Handler mHandler = new Handler();

    @Override
    public void search(String url, String content, int pageNum, final OnSearchListener onSearchListener) {
        // post 请求
        OkHttpClient client = new OkHttpClient.Builder().build();
        LogUtils.d("post 提交的地址 ：" + url);
        RequestBody requestBody = new FormBody.Builder()
                .add("k", content).build();
        Request requestPost = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSearchListener.onSearchFailed("网络异常！");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final Article articleData = gson.fromJson(result, Article.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSearchListener.onSearchSuccess(articleData);
                    }
                });
            }
        });
    }
}
