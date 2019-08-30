package com.zohar.wanandroid.model.search;

import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.search.HotSearchData;
import com.zohar.wanandroid.http.HttpRequestUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/30 11:39
 * Describe:
 */
public class HotSearchModel implements IHotSearchModel {

    private Handler mHandler = new Handler();

    @Override
    public void hotSearch(String url, final OnHotSearchListener onHotSearchListener) {
        Call call = HttpRequestUtils.sendHttpGetRequest(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onHotSearchListener.hotSearchFailed("网络异常！");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Gson gson = new Gson();
                final HotSearchData hotSearchData = gson.fromJson(result, HotSearchData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onHotSearchListener.hotSearchSuccess(hotSearchData);
                    }
                });
            }
        });
    }
}
