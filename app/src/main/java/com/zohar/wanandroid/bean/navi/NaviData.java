package com.zohar.wanandroid.bean.navi;

import java.util.List;

/**
 * Created by zohar on 2019/8/28 15:50
 * Describe:
 */
public class NaviData {

    private List<NaviArticles> data;
    private int errorCode;
    private String errorMsg;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<NaviArticles> getData() {
        return data;
    }

    public void setData(List<NaviArticles> data) {
        this.data = data;
    }
}
