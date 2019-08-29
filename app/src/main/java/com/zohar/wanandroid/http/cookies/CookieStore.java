package com.zohar.wanandroid.http.cookies;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by zohar on 2019/8/29 16:22
 * Describe:
 * 用于管理Cookie的接口CookieStore
 * <p>
 * 参考链接：https://blog.csdn.net/u012527802/article/details/81220301
 */
public interface CookieStore {
    /**
     * 添加cookie
     */
    void add(HttpUrl httpUrl, Cookie cookie);

    /**
     * 添加指定httpurl cookie集合
     */
    void add(HttpUrl httpUrl, List<Cookie> cookies);

    /**
     * 根据HttpUrl从缓存中读取cookie集合
     */
    List<Cookie> get(HttpUrl httpUrl);

    /**
     * 获取全部缓存cookie
     */
    List<Cookie> getCookies();

    /**
     * 移除指定httpurl cookie集合
     */
    boolean remove(HttpUrl httpUrl, Cookie cookie);

    /**
     * 移除所有cookie
     */
    boolean removeAll();
}
