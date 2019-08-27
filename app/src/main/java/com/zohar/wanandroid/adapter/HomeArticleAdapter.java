package com.zohar.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zohar.wanandroid.ArticllDetailActivity;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.bean.home.Datas;
import com.zohar.wanandroid.config.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/8 17:08
 * Describe:
 */
public class HomeArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Datas> articles = new ArrayList<>();
    private Context mContext;

    // 正常内容
    private final static int TYPE_CONTENT = 0;
    // 下来刷新
    private final static int TYPE_FOOTER = 1;


    public HomeArticleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == articles.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_CONTENT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article, viewGroup, false);
            final ArticleViewHolder holder = new ArticleViewHolder(view);
            // 设置点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition(); // 获取当前点击的位置
                    Datas article = articles.get(position);
                    // 跳转到内容详情页面
                    String articleLink = article.getLink();
                    Intent intent = new Intent(mContext, ArticllDetailActivity.class);
                    intent.putExtra(AppConstants.ARTICLE_FROM_HOME, articleLink); // 传递链接
                    intent.putExtra(AppConstants.ARTICLE_TITLE_FROM_HOME, article.getTitle()); // 传递标题
                    mContext.startActivity(intent);
                }
            });
            return holder;
        } else {
            // footer
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article_footer, viewGroup, false);
            return new FooterViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            // 上拉加载

        } else {
            // 正常内容
            // viewholder绑定的数据
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
            Datas article = articles.get(position);
            articleViewHolder.title.setText(article.getTitle());
            articleViewHolder.author.setText(article.getAuthor());
            articleViewHolder.date.setText(article.getNiceDate());
            articleViewHolder.chapterName.setText(article.getChapterName());
            articleViewHolder.superChapterName.setText(article.getSuperChapterName());
            //LogUtils.d(article.toString());
            if (article.isFresh()) {
                articleViewHolder.refresh.setVisibility(View.VISIBLE);
            }
            // 处理tag
            if (article.getTags().size() != 0) {
                articleViewHolder.tag.setVisibility(View.VISIBLE);
                articleViewHolder.tag.setText(article.getTags().get(0).getName());
            }
        }

    }

    @Override
    public int getItemCount() {
        return articles.size() + 1;
    }


    class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView title;
        TextView author;
        TextView chapterName;
        TextView superChapterName;
        TextView date; // niceDate
        ImageView collectImageView;
        TextView refresh;
        TextView tag;

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
        }
    }

    /**
     * 添加内容，然后更新
     *
     * @param addArticle
     */
    public void addArticle(List<Datas> addArticle) {
        articles.addAll(addArticle);
        notifyItemRangeChanged(articles.size() - addArticle.size(), addArticle.size());
    }

    /**
     * 清空list
     */
    public void clearArticle(){
        articles.clear();
    }
}
