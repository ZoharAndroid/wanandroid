package com.zohar.wanandroid.model.register;

import com.zohar.wanandroid.bean.register.RegisterData;

/**
 * Created by zohar on 2019/8/29 10:55
 * Describe:
 */
public interface OnRegisterListener {

    void registerSuccess(RegisterData registerData);
    void registerFailed(String msg);
}
