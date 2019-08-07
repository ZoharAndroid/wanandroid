package com.zohar.wanandroid.base;

import android.support.annotation.StringRes;

/**
 * Created by zohar on 2019/7/30 10:32
 * Describe:
 */
public interface IMVPView {

    void showLoading();
    void hideLoading();
    void showError(String errorMsg);
    void showError(@StringRes int resId);
}
