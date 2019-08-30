package com.zohar.wanandroid.bean.search;

/**
 * Created by zohar on 2019/8/30 11:32
 * Describe:
 */
public class HotSearch {
    /**
     *  "id": 6,
     *       "link": "",
     *       "name": "面试",
     *       "order": 1,
     *       "visible": 1
     */

    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    @Override
    public String toString() {
        return "HotSearch{" +
                "name='" + name + '\'' +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
