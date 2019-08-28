package com.zohar.wanandroid.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.view.flowlayout.TagFlowLayout;
import com.zohar.wanandroid.R;

/**
 * Created by zohar on 2019/8/28 16:44
 * Describe:
 */
public class NaviViewHolder extends RecyclerView.ViewHolder {

    public TagFlowLayout mFlowLayout;
    public TextView mTextViewTitle;

    public NaviViewHolder(@NonNull View itemView) {
        super(itemView);

        mFlowLayout = itemView.findViewById(R.id.item_navi_container);
        mTextViewTitle = itemView.findViewById(R.id.item_navi_text_view);
    }
}
