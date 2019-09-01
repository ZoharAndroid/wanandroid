package com.zohar.wanandroid.bean.collect;

import java.util.List;

/**
 * Created by zohar on 2019/9/1 10:54
 * Describe:
 */
public class CollectListData {

    /**
     *  "curPage": 1,
     *         "datas": []
     *         ,
     *         "offset": 0,
     *         "over": true,
     *         "pageCount": 1,
     *         "size": 20,
     *         "total": 12
     */
    private int curPage;
    private List<Collect> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<Collect> getDatas() {
        return datas;
    }

    public void setDatas(List<Collect> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
