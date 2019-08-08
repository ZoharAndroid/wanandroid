package com.zohar.wanandroid.http;

import com.zohar.wanandroid.bean.home.Article;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zohar on 2019/8/8 15:06
 * Describe:
 */
public interface GetAritcleResponse {
    /**
     * 获取首页文章列表
     *
     * @param num 页数
     * @return 文章列表
     */
    @GET("article/list/{num}/json")
    Call<Article> getHomeArticle(@Path("num") int num);
}
