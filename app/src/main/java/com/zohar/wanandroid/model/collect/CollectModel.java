package com.zohar.wanandroid.model.collect;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.http.cookies.PersistentCookieStore;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.utils.MD5Utils;
import com.zohar.wanandroid.utils.SharePreferenceUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

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
    public void collect(Context context, String url, int articleId, final OnCollectListener onCollectListener) {
        // post提交
        OkHttpClient client = new OkHttpClient.Builder().build();

        String userName = null;
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.name().equals(AppConstants.LOGIN_USER_NAME)){
                userName = cookie.value();
                break;
            }
        }
        RequestBody requestBody = new FormBody.Builder().build();
        Request requestPost = new Request.Builder()
                .post(requestBody)
                .url(url)
                .addHeader("Cookie","loginUserName=" + userName)
                .addHeader("Cookie","loginUserPassword=" + SharePreferenceUtils.getLoginPassword(context))
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
                LogUtils.d(result);
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
