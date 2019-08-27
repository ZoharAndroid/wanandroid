package com.zohar.wanandroid.model.home;

import com.zohar.wanandroid.bean.home.Article;
import com.zohar.wanandroid.bean.home.banner.Banner;

/**
 * Created by zohar on 2019/8/8 16:30
 * Describe:
 *  文章首页的文章列表请求回调接口
 */
public interface OnHttpListener {

    /**
     * 获取首页文章列表请求
     *
     * @param article
     */
    void httpSuccess(Article article);

    /**
     * 请求失败
     *
     * @param msg
     */
    void httpFailed(String msg);


}
