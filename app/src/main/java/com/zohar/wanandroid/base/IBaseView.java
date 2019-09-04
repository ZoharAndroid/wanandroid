package com.zohar.wanandroid.base;

import android.content.Context;

/**
 * Created by zohar on 2019/9/2 16:47
 * Describe:
 *
 * View的接口基类
 */
public interface IBaseView {

    /**
     * 显示加载
     */
    void showLoadingView();

    /**
     * 隐藏加载框
     */
    void hideLoadingView();


    /**
     * 获取上下文
     *
     * @return Context
     */
    Context getContext();
}
