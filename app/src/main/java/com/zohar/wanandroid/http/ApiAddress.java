package com.zohar.wanandroid.http;

/**
 * Created by zohar on 2019/8/8 14:53
 * Describe:
 */
public class ApiAddress {

    public static final String Host = "https://www.wanandroid.com/";

    public static String homeAritcleAddress(int num){
        StringBuilder sb = new StringBuilder(Host);
        sb.append("article/list/");
        sb.append(num);
        sb.append("/json");
        return sb.toString();
    }

}
