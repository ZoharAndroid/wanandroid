package com.zohar.wanandroid.base;

/**
 * Created by ZHANGZHIHONG$ on 2019/9/4 21:39
 * Describe:
 */
public interface IBasePresenter<V extends IBaseView> {

    /**
     * 依附生命view
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 分离View
     */
    void detachView();

    /**
     * 判断View是否已经销毁
     *
     * @return
     */
    boolean isViewAttached();
}
