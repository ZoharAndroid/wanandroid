package com.zohar.wanandroid.bean.search;

import java.util.List;

/**
 * Created by zohar on 2019/8/30 11:32
 * Describe:
 */
public class HotSearchData {

    private List<HotSearch> data;
    private String errorMsg;
    private int errorCode;

    public List<HotSearch> getData() {
        return data;
    }

    public void setData(List<HotSearch> data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
