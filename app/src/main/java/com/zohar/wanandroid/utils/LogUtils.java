package com.zohar.wanandroid.utils;

import android.util.Log;

/**
 * Created by zohar on 2019/8/8 16:32
 * Describe:
 */
public class LogUtils {

    private static final String TAG = "LogUtils";


    public static void d(String msg){
        Log.d(TAG, "====== " + msg + " ======");
    }
}
