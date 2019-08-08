package com.zohar.wanandroid.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zohar on 2019/8/8 8:43
 * Describe:
 */
public class ToastUtils {

    /**
     * 显示toast
     *
     * @param context cotext
     * @param msg 需要显示的信息
     */
    public static void toastShow(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
