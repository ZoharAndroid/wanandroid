package com.zohar.wanandroid.bean.knowledge;

import java.util.List;

/**
 * Created by zohar on 2019/8/27 13:31
 * Describe:
 *  知识体系的主bean
 */
public class Knowledge {

    private List<SubKnowledge> data;
    private int errorCode;
    private String errorMsg;

    public List<SubKnowledge> getData() {
        return data;
    }

    public void setData(List<SubKnowledge> data) {
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
