package com.zohar.wanandroid.model.collect;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.http.HttpRequestUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zohar on 2019/9/1 9:46
 * Describe:
 */
public class CollectListModel implements ICollectListModel{

    private Handler mHandler = new Handler();

    @Override
    public void collectList(Context context, String url, int pageNum, final OnCollectListListener onCollectListener) {
        Call call = HttpRequestUtils.sendHttpGetRequest(context, url);
        call.enqueue(new Callback() {
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
                final CollectData collectListData = gson.fromJson(result, CollectData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onCollectListener.collectSuccess(collectListData);
                    }
                });
            }
        });
    }
}
