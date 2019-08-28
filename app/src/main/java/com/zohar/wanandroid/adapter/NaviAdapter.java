package com.zohar.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.viewholder.NaviViewHolder;
import com.zohar.wanandroid.bean.navi.NaviArticleDetail;
import com.zohar.wanandroid.bean.navi.NaviArticles;
import com.zohar.wanandroid.utils.ScreenUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/28 16:39
 * Describe:
 */
public class NaviAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<NaviArticles> mArticles;

    public NaviAdapter(Context context, List<NaviArticles> articles) {
        mContext = context;
        mArticles = articles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_navi, viewGroup, false);
        NaviViewHolder holder = new NaviViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, int position) {
        final NaviViewHolder holder = (NaviViewHolder)viewHolder;
        NaviArticles naviArticles = mArticles.get(position);
        holder.mTextViewTitle.setText(naviArticles.getName());
        // 获取详细数据
        final List<NaviArticleDetail> details = mArticles.get(position).getArticles();
        // 设置tagflowlayout
        holder.mFlowLayout.setAdapter(new TagAdapter<NaviArticleDetail>(details) {

            @Override
            public View getView(FlowLayout parent, int position, NaviArticleDetail naviArticleDetail) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item_flow_tv , parent, false);
                if (details == null){
                    return null;
                }
                String name = naviArticleDetail.getTitle();
                tv.setText(name);
                tv.setTextColor(ScreenUtil.randomColor());
                tv.setPadding(ScreenUtil.dp2px(mContext, 10), ScreenUtil.dp2px(mContext,10),
                        ScreenUtil.dp2px(mContext,10), ScreenUtil.dp2px(mContext,10));
                holder.mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        return false;
                    }
                });
                return tv;
            }
        });

        // 获取每个item中的数据
        for (int i = 0; i < details.size(); i++){
            TextView textView = new TextView(mContext);
            textView.setText(details.get(i).getTitle());
            //holder.mViewContainer.addView(textView);
        }


    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
