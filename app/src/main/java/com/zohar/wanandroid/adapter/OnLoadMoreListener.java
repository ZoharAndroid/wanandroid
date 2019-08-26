package com.zohar.wanandroid.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;

import com.zohar.wanandroid.utils.LogUtils;

/**
 * Created by zohar on 2019/8/26 7:47
 * Describe:
 */
public abstract class OnLoadMoreListener extends OnScrollListener {

    private  boolean isScrolled; // 是否可以滑动
    // 总的items数目
    private int mCountItem;
    // 手机屏幕可以显示的item数目
    private int mLastIndex;

    /**
     * 加载更多抽象方法
     */
    public abstract  void onLoadMore();

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        // 滑动时的回调
        if ( recyclerView.getLayoutManager() instanceof LinearLayoutManager){
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            mCountItem = layoutManager.getItemCount();
            // lastindex 索引是从0开始的
            mLastIndex = ((LinearLayoutManager)layoutManager).findLastCompletelyVisibleItemPosition();
        }
        if (isScrolled && mCountItem != mLastIndex && mLastIndex == mCountItem - 1){
            onLoadMore();
        }
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        // 滑动状态变化的回调方法
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING){
            isScrolled = true;
        }else{
            isScrolled = false;
        }
    }
}
