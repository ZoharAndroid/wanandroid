package com.zohar.wanandroid.view.login;

import com.zohar.wanandroid.base.IBaseView;
import com.zohar.wanandroid.bean.register.RegisterData;

/**
 * Created by zohar on 2019/8/30 8:40
 * Describe:
 */
public interface ILoginOutView extends IBaseView {

    void showLoadingView();
    void hideLoadingView();

    void logoutSuccess(RegisterData registerData);
    void logoutFailed(String msg);
}
