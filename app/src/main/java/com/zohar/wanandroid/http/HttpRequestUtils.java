package com.zohar.wanandroid.http;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.utils.LogUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/27 9:12
 * Describe:
 *  Http请求工具类
 */
public class HttpRequestUtils {

    public static Call sendHttpGetRequest(String url){
        OkHttpClient client = new OkHttpClient();

        LogUtils.d( "请求的地址为:" + url);

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        return client.newCall(request);
    }
}
