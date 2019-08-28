package com.zohar.wanandroid.http;

/**
 * Created by zohar on 2019/8/8 14:53
 * Describe:
 */
public class ApiAddress {

    public static final String HOST = "https://www.wanandroid.com";

    /**
     * 获取首页文章列表
     *
     * @param num
     * @return
     */
    public static String homeAritcleAddress(int num){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/article/list/");
        sb.append(num);
        sb.append("/json");
        return sb.toString();
    }

    /**
     * 首页banner
     */
    public static final String HOME_BANNER =  HOST + "/banner/json";

    /**
     * 获取体系数据
     */
    public static final String KNOWLEDGE_TREE = HOST + "/tree/json";

    /**
     * 知识体系下的文章
     *  e.g. https://www.wanandroid.com/article/list/0/json?cid=60
     * @param pageNum
     * @param cid
     * @return
     */
    public static  String KNOWLEDGE_TREE_ARTICLE(int pageNum, int cid){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/article/list/");
        sb.append(pageNum);
        sb.append("/json?cid=");
        sb.append(cid);
        return sb.toString();
    }

    /**
     * 获取微信公众号列表内容
     */
    public static final String WEICHAT_LIST_ADDRESS = HOST + "/wxarticle/chapters/json";

    /**
     * 获取公众号对应id的文章
     * https://wanandroid.com/wxarticle/list/408/1/json
     *
     * @param cid 公众号id
     * @param pageNum 页码
     * @return
     */
    public static String WECHAT_ARTICLE_ADDRESS(int cid, int pageNum){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/wxarticle/list/");
        sb.append(cid);
        sb.append("/");
        sb.append(pageNum);
        sb.append("/json");
        return sb.toString();
    }

}
