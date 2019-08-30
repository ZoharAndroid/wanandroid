package com.zohar.wanandroid.model.navi;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.navi.NaviData;
import com.zohar.wanandroid.http.HttpRequestUtils;
import com.zohar.wanandroid.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/28 16:26
 * Describe:
 */
public class NaviModel implements INaviModel {

    @Override
    public void sendHttpRequest(String url, final OnHttpListener httpListener) {
        Call call = HttpRequestUtils.sendHttpGetRequest(url);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                httpListener.httpFailed("网络请求失败！");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String result = response.body().string();
                Gson gson = new Gson();
                NaviData data = gson.fromJson(result, NaviData.class);
                httpListener.httpSuccess(data);
            }
        });
    }
}
