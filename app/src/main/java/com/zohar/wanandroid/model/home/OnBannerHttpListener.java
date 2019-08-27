package com.zohar.wanandroid.model.home;

import com.zohar.wanandroid.bean.home.banner.Banner;

/**
 * Created by zohar on 2019/8/27 9:08
 * Describe:
 *  文章首页的banner请求回调接口
 */
public interface OnBannerHttpListener {
    /**
     * 请求失败
     *
     * @param msg
     */
    void httpFailed(String msg);

    /**
     * 获取首页banner请求
     *
     * @param banner
     */
    void bannerHttpSuccess(Banner banner);
}
