package com.zohar.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zohar.wanandroid.R;
import com.zohar.wanandroid.bean.home.Datas;
import com.zohar.wanandroid.utils.LogUtils;

import java.util.List;

/**
 * Created by zohar on 2019/8/8 17:08
 * Describe:
 */
public class HomeArticleAdapter extends RecyclerView.Adapter<HomeArticleAdapter.ArticleViewHolder> {

    private List<Datas> articles;
    private Context mContext;

    public HomeArticleAdapter(Context context, List<Datas> articles) {
        mContext = context;
        this.articles = articles;
        LogUtils.d("传递过来的数据为：" + articles.toString() );
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article, viewGroup, false);
        ArticleViewHolder holder = new ArticleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( ArticleViewHolder articleViewHolder, int position) {
        Datas article = articles.get(position);
        articleViewHolder.title.setText(article.getTitle());
        articleViewHolder.author.setText(article.getAuthor());
        articleViewHolder.date.setText(article.getNiceDate());
        articleViewHolder.chapterName.setText(article.getChapterName());
        articleViewHolder.superChapterName.setText(article.getSuperChapterName());
        //LogUtils.d(article.toString());
        if (article.isFresh()){
            articleViewHolder.refresh.setVisibility(View.VISIBLE);
        }
        // 处理tag
        if (article.getTags().size() != 0){
            articleViewHolder.tag.setVisibility(View.VISIBLE);
            articleViewHolder.tag.setText(article.getTags().get(0).getName());
        }

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    static class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView author;
        TextView chapterName;
        TextView superChapterName;
        TextView date; // niceDate
        ImageView collectImageView;
        TextView refresh;
        TextView tag;

        public ArticleViewHolder( View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            author = itemView.findViewById(R.id.item_author);
            chapterName = itemView.findViewById(R.id.item_chapter_name);
            superChapterName = itemView.findViewById(R.id.item_super_chapter_name);
            date = itemView.findViewById(R.id.item_date);
            collectImageView = itemView.findViewById(R.id.item_collect_image_view);
            refresh = itemView.findViewById(R.id.item_tag_refresh);
            tag = itemView.findViewById(R.id.item_tag);
        }
    }
}
