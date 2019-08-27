package com.zohar.wanandroid.bean.home.banner;

import java.util.List;

/**
 * Created by zohar on 2019/8/26 23:07
 * Describe:
 * 首页轮播图 json数据
 */
public class Banner {

    /**
     * {
     *     "data" : [
     *          {
     *              "desc": "华为工程师推荐的一份学习资料",
     *              "id": 27,
     *              "imagePath": "https://wanandroid.com/blogimgs/81286041-eefe-4f9a-a11d-6876c1866d1e.jpeg",
     *              "isVisible": 1,
     *              "order": 1,
     *              "title": "华为工程师推荐的一份学习资料",
     *              "type": 0,
     *              "url": "https://mp.weixin.qq.com/s/KwpOzDygmH5af6t_QW7riw"
     *          },
     *          {},
     *     ],
     *    "errorCode": 0,
     *   "errorMsg": ""
     * }
     */

    private List<BannerData> data;
    private int errorCode;
    private String errorMsg;

    public List<BannerData> getData() {
        return data;
    }

    public void setData(List<BannerData> data) {
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

    @Override
    public String toString() {
        return "Banner{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
