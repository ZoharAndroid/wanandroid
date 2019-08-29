package com.zohar.wanandroid.bean.register;

/**
 * Created by zohar on 2019/8/29 11:31
 * Describe:
 */
public class RegisterData {

    /**
     * {
     *     "data": {
     *         "admin": false,
     *         "chapterTops": [],
     *         "collectIds": [],
     *         "email": "",
     *         "icon": "",
     *         "id": 29523,
     *         "nickname": "ZoharAndroid",
     *         "password": "",
     *         "token": "",
     *         "type": 0,
     *         "username": "ZoharAndroid"
     *     },
     *     "errorCode": 0,
     *     "errorMsg": ""
     *
     *     {
     *     "data": null,
     *     "errorCode": -1,
     *     "errorMsg": "用户名已经被注册！"
     * }
     * }
     */

    private Register data;
    private int errorCode;
    private String errorMsg;

    public Register getData() {
        return data;
    }

    public void setData(Register data) {
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
