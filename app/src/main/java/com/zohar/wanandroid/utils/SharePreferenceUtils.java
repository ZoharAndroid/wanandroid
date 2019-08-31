package com.zohar.wanandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.http.cookies.PersistentCookieStore;

/**
 * Created by zohar on 2019/8/29 22:12
 * Describe:
 */
public class SharePreferenceUtils {

    public static void saveUserInfo(Context context, String username, String password){
        // 保存用户相关信息
        SharedPreferences sp = context.getSharedPreferences(AppConstants.COOKIE_PREFS, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(AppConstants.LOGIN_PASSWORD, MD5Utils.convertMD5(MD5Utils.string2MD5(password)));
        editor.putString(AppConstants.CURRENT_USER_NAME, username);
        editor.apply();
    }

    /**
     * 清除所有信息
     *
     * @param context
     */
    public static void clearUserInfo(Context context){
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        cookieStore.removeAll();
    }


    /**
     * 获取username
     * @param mContext
     * @return
     */
    public static String getLoginUserName(Context mContext){
        return mContext.getSharedPreferences(AppConstants.COOKIE_PREFS, 0).getString(AppConstants.LOGIN_USER_NAME, null);
    }

    /**
     * 获取用户相关信息
     *
     * @param mContext
     * @return
     */
    public static String getLoginPassword(Context mContext){
        return mContext.getSharedPreferences(AppConstants.COOKIE_PREFS, 0).getString(AppConstants.LOGIN_PASSWORD, null);
    }
}
