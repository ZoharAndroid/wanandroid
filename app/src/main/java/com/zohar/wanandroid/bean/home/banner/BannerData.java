package com.zohar.wanandroid.bean.home.banner;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * Created by zohar on 2019/8/26 23:11
 * Describe:
 *  轮播图中的item中的data json格式
 */
public class BannerData extends SimpleBannerInfo {

    /**
     *  {
     *      *              "desc": "华为工程师推荐的一份学习资料",
     *      *              "id": 27,
     *      *              "imagePath": "https://wanandroid.com/blogimgs/81286041-eefe-4f9a-a11d-6876c1866d1e.jpeg",
     *      *              "isVisible": 1,
     *      *              "order": 1,
     *      *              "title": "华为工程师推荐的一份学习资料",
     *      *              "type": 0,
     *      *              "url": "https://mp.weixin.qq.com/s/KwpOzDygmH5af6t_QW7riw"
     *      *          }
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "BannerData{" +
                "imagePath='" + imagePath + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public Object getXBannerUrl() {
        return url;
    }

    @Override
    public String getXBannerTitle() {
        return title;
    }
}