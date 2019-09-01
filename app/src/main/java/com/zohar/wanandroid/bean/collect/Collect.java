package com.zohar.wanandroid.bean.collect;

/**
 * Created by zohar on 2019/9/1 10:53
 * Describe:
 */
public class Collect {

    /**
     * "author": "逐梦々少年",
     *                 "chapterId": 200,
     *                 "chapterName": "https",
     *                 "courseId": 13,
     *                 "desc": "",
     *                 "envelopePic": "",
     *                 "id": 82920,
     *                 "link": "https://www.jianshu.com/p/f3c0827b9be6",
     *                 "niceDate": "11小时前",
     *                 "origin": "",
     *                 "originId": 9065,
     *                 "publishTime": 1567261141000,
     *                 "title": "从设计HTTPS开始分析HTTPS协议",
     *                 "userId": 29513,
     *                 "visible": 0,
     *                 "zan": 0
     */

    private String author;
    private int chapterId;
    private String chapterName;
    private int courseId;
    private String desc;
    private String envelopePic;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private int originId = -1;
    private long publishTime;
    private String title;
    private int userId;
    private  int visible;
    private int zan;

    @Override
    public String toString() {
        return "Collect{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnvelopePic() {
        return envelopePic;
    }

    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getZan() {
        return zan;
    }

    public void setZan(int zan) {
        this.zan = zan;
    }
}
