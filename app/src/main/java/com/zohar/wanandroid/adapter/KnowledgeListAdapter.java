package com.zohar.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohar.wanandroid.ArticllDetailActivity;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.viewholder.FooterViewHolder;
import com.zohar.wanandroid.bean.home.Datas;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.utils.LogUtils;
import com.zohar.wanandroid.adapter.viewholder.ArticleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/8 17:08
 * Describe:
 */
public class KnowledgeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Datas> articles = new ArrayList<>();
    private Context mContext;
    // 正常内容
    private final static int TYPE_CONTENT = 0;
    // footer
    private final static int TYPE_FOOTER = 1;

    public KnowledgeListAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == articles.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_CONTENT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article, viewGroup, false);
            final ArticleViewHolder holder = new ArticleViewHolder(view);
            // 设置点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition(); // 获取当前点击的位置
                    Datas article = articles.get(position - 1);
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
        // 正常内容
        // viewholder绑定的数据
        ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
        Datas article = articles.get(position);

        articleViewHolder.title.setText(article.getTitle());
        articleViewHolder.author.setText(article.getAuthor());
        articleViewHolder.date.setText(article.getNiceDate());
        articleViewHolder.chapterName.setText(article.getChapterName());
        articleViewHolder.superChapterName.setText(article.getSuperChapterName());
        if (article.isFresh()) {
            articleViewHolder.refresh.setVisibility(View.VISIBLE);
        }
        // 处理tag
        if (article.getTags().size() != 0) {
            articleViewHolder.tag.setVisibility(View.VISIBLE);
            articleViewHolder.tag.setText(article.getTags().get(0).getName());
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }



    /**
     * 添加内容，然后更新
     *
     * @param addArticle
     */
    public void addArticle(List<Datas> addArticle) {
        articles.addAll(addArticle);
        LogUtils.d(articles.toString());
        notifyItemRangeChanged(articles.size() - addArticle.size() + 1, addArticle.size());
    }

    /**
     * 清空list
     */
    public void clearArticle() {
        articles.clear();
    }
}
