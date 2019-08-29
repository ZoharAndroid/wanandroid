package com.zohar.wanandroid.http.cookies;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by zohar on 2019/8/29 16:24
 * Describe:
 *
 * CookieJarImpl类实现CookieJar接口，然后用CookieStore去接管saveFromResponse、loadForRequest 这两个方法：
 */
public class CookieJarImpl implements CookieJar {

    private CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore) {
        if(cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null.");
        }
        this.cookieStore = cookieStore;
    }

    @NotNull
    @Override
    public synchronized List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        return cookieStore.get(httpUrl);
    }

    @Override
    public synchronized void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> cookies) {
        this.cookieStore.add(httpUrl, cookies);
    }


    /**
     * 获取Cookies Store
     *
     * @return Cookie Store
     */
    public CookieStore getCookieStore(){
        return this.cookieStore;
    }
}
