package com.zohar.wanandroid.http;

import android.content.Context;

import com.google.gson.Gson;
import com.zohar.wanandroid.bean.home.Article;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zohar on 2019/8/27 9:12
 * Describe:
 *  Http请求工具类
 */
public class HttpRequestUtils {

    public static Call sendHttpGetRequest(Context context,String url){
        OkHttpClient client = new OkHttpClient();

        LogUtils.d( "请求的地址为:" + url);

        String userName = null;
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.name().equals(AppConstants.LOGIN_USER_NAME)){
                userName = cookie.value();
                break;
            }
        }
        Request request = new Request.Builder()
                .get()
                .url(url)
                .addHeader("Cookie","loginUserName=" + userName)
                .addHeader("Cookie","loginUserPassword=" + SharePreferenceUtils.getLoginPassword(context))
                .build();

        return client.newCall(request);
    }
}
