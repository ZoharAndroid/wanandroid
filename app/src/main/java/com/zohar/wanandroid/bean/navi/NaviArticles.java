package com.zohar.wanandroid.bean.navi;

import java.util.List;

/**
 * Created by zohar on 2019/8/28 15:55
 * Describe:
 */
public class NaviArticles {
    private List<NaviArticleDetail> articles;
    private int cid;
    private String name;

    public List<NaviArticleDetail> getArticles() {
        return articles;
    }

    public void setArticles(List<NaviArticleDetail> articles) {
        this.articles = articles;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NaviArticles{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
