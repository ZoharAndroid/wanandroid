package com.zohar.wanandroid.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zohar.wanandroid.R;

/**
 * Created by zohar on 2019/8/28 8:40
 * Describe:
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {


    public View itemView;
    public TextView title;
    public TextView author;
    public TextView chapterName;
    public TextView superChapterName;
    public TextView date; // niceDate
    public ImageView collectImageView;
    public TextView refresh;
    public TextView tag;
    public ImageView image;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        title = itemView.findViewById(R.id.item_title);
        author = itemView.findViewById(R.id.item_author);
        chapterName = itemView.findViewById(R.id.item_chapter_name);
        superChapterName = itemView.findViewById(R.id.item_super_chapter_name);
        date = itemView.findViewById(R.id.item_date);
        collectImageView = itemView.findViewById(R.id.item_collect_image_view);
        refresh = itemView.findViewById(R.id.item_tag_refresh);
        tag = itemView.findViewById(R.id.item_tag);
        image = itemView.findViewById(R.id.item_image);
    }
}
