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


    /**
     * 项目名称地址
     *
     * https://www.wanandroid.com/project/tree/json
     */
    public final static String PROJECT_CATEGORIES_ADDRESS = HOST + "/project/tree/json";


    /**
     * 项目列表地址：根据id来获取对应的项目列表
     *
     * https://www.wanandroid.com/project/list/1/json?cid=294
     * @return
     */
    public static String PROJECT_LIST_ADDRESS(int cid, int pageNum){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/project/list/");
        sb.append(pageNum);
        sb.append("/json?cid=");
        sb.append(cid);
        return sb.toString();
    }

    /**
     * 导航数据地址
     *
     * https://www.wanandroid.com/navi/json
     */
    public static final String NAVI_ADDRESS = HOST + "/navi/json";

    /**
     * 注册地址
     * https://www.wanandroid.com/user/register
     */
    public static final String REGISTER_ADDRESS = HOST + "/user/register";

    /**
     * 登录地址
     */
    public static final String LOGIN_ADDRESS = "https://www.wanandroid.com/user/login";

    /**
     * 退出登录地址
     */
    public static final String  LOGOUT_ADDRESS = "https://www.wanandroid.com/user/logout/json";


    /**
     * 热搜地址
     */
    public static final String HOT_SEARCH_ADDRESS = "https://www.wanandroid.com//hotkey/json";

    /**
     * 搜索内容地址
     *
     * @param pageNum 第几页
     *
     * @return
     */
    public static  String SEARCH_ADDRESS(int pageNum){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/article/query/");
        sb.append(pageNum);
        sb.append("/json");
        return sb.toString();
    }

    /**
     * https://www.wanandroid.com/lg/collect/1165/json
     *方法：POST
     * 参数： 文章id，拼接在链接中。
     * @param articleId
     * @return
     */
    public static String COLLECT_SITE_ADDRESS(int articleId){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/lg/collect/");
        sb.append(articleId);
        sb.append("/json");
        return sb.toString();
    }


    /**
     * https://www.wanandroid.com/lg/collect/list/0/json
     *
     * 方法：GET
     * 参数： 页码：拼接在链接中，从0开始。
     *
     * @return
     */
    public static  String COLLECT_LIST_ADDRESS(int pageNum){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/lg/collect/list/");
        sb.append(pageNum);
        sb.append("/json");
        return sb.toString();
    }


    /**
     * https://www.wanandroid.com/lg/uncollect/2805/json
     *
     * 方法：POST
     * 参数：
     * 	id:拼接在链接上
     * 	originId:列表页下发，无则为-1
     *
     * @param articleId
     * @param originId
     * @return
     */
    public static String CANCEL_MY_COLLECT_ADDRESS(int articleId){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/lg/uncollect/");
        sb.append(articleId);
        sb.append("/json");
        return sb.toString();
    }

    /**
     * https://www.wanandroid.com/lg/uncollect_originId/2333/json
     *
     * 方法：POST
     * 参数：
     * 	id:拼接在链接上
     *
     * @return
     */
    public static String CANCEL_ARTICLE_LIST_COLLECT_ADDRESS(int articleId){
        StringBuilder sb = new StringBuilder(HOST);
        sb.append("/lg/uncollect_originId/");
        sb.append(articleId);
        sb.append("/json");
        return sb.toString();
    }
}
