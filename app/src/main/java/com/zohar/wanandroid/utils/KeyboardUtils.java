package com.zohar.wanandroid.utils;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by zohar on 2019/7/29 22:00
 * Describe: 键盘的工具类
 */
public class KeyboardUtils {

    private KeyboardUtils(){}

    /**
     * 打开软键盘
     *
     * @param context context
     * @param editText EditText View
     */
    public static void openKeyboard(Context context, EditText editText){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            // 显示软件盘
            imm.showSoftInput(editText, 0);
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context context
     * @param editText EditText View
     */
    public static void closeKeyboard(Context context, EditText editText){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if ( imm != null){
            // 隐藏
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     *
     * @param context
     */
    public static void toggleKeyboard(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if ( imm != null){
            // 隐藏
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }
}
