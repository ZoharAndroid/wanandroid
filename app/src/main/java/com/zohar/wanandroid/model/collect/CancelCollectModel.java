package com.zohar.wanandroid.model.collect;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.http.cookies.PersistentCookieStore;
import com.zohar.wanandroid.utils.LogUtils;
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
 * Created by zohar on 2019/9/1 12:10
 * Describe:
 */
public class CancelCollectModel{

    private Handler mHandler = new Handler();
    /**
     * 我的收藏列表上的取消
     *
     * https://www.wanandroid.com/lg/uncollect/2805/json
     *
     * 方法：POST
     * 参数：
     * 	id:拼接在链接上
     * 	originId:列表页下发，无则为-1
     *
     * @param context
     */
    public void cancelMyCollect(Context context, String url, int artcileId, int originId, final OnCancelCollectListener onCancelCollectListener){
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
        RequestBody requestBody = new FormBody.Builder()
                .add("originId", String.valueOf(originId))
                .build();
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
                        onCancelCollectListener.cancelCollectFailed("网络异常！");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                LogUtils.d(result);
                Gson gson = new Gson();
                final CollectData data = gson.fromJson(result, CollectData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onCancelCollectListener.cancelCollectSuccess(data);
                    }
                });
            }
        });
    }


    /**
     * 文章列表上的收藏取消
     *
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     *
     * 方法：POST
     * 参数：
     * 	id:拼接在链接上
     */
    public void cancelArticleListCollect(Context context, String url, int articleId, final OnCancelCollectListener onCancelCollectListener){
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
        RequestBody requestBody = new FormBody.Builder()
                .build();
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
                        onCancelCollectListener.cancelCollectFailed("网络异常！");
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                LogUtils.d(result);
                Gson gson = new Gson();
                final CollectData data = gson.fromJson(result, CollectData.class);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onCancelCollectListener.cancelCollectSuccess(data);
                    }
                });
            }
        });
    }
}
