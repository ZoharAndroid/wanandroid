package com.zohar.wanandroid.bean.collect;

import java.util.List;

/**
 * Created by zohar on 2019/9/1 10:53
 * Describe:
 */
public class CollectData {

    /**
     *
     *     "data": {},
     *  "errorCode": 0,
     *     "errorMsg":
     */
    private CollectListData data;
    private int errorCode;
    private String errorMsg;

    public CollectListData getData() {
        return data;
    }

    public void setData(CollectListData data) {
        this.data = data;
    }

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
}
