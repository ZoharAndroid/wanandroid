package com.zohar.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohar.wanandroid.ArticllDetailActivity;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.adapter.viewholder.ArticleViewHolder;
import com.zohar.wanandroid.adapter.viewholder.FooterViewHolder;
import com.zohar.wanandroid.bean.collect.Collect;
import com.zohar.wanandroid.bean.collect.CollectData;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.presenter.collect.CancelCollectPresenter;
import com.zohar.wanandroid.utils.ToastUtils;
import com.zohar.wanandroid.view.collect.ICancelCollectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/8 17:08
 * Describe:
 */
public class CollectListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ICancelCollectView {

    private List<Collect> articles = new ArrayList<>();
    private Context mContext;
    // 正常内容
    private final static int TYPE_CONTENT = 0;
    // footer
    private final static int TYPE_FOOTER = 1;
    private final static int TYPE_FOOTER_NO = 2;

    private boolean isFooterShow = false;
    private int mPosition;

    public CollectListAdapter(Context context) {
        mContext = context;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == articles.size()) {
            if (isFooterShow) {
                return TYPE_FOOTER;
            } else {
                return TYPE_FOOTER_NO;
            }
        }
        return TYPE_CONTENT;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        FooterViewHolder footerHolder;
        View view;
        if (viewType == TYPE_CONTENT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article, viewGroup, false);
            final ArticleViewHolder holder = new ArticleViewHolder(view);
            // 设置点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition(); // 获取当前点击的位置
                    Collect article = articles.get(position);
                    // 跳转到内容详情页面
                    String articleLink = article.getLink();
                    Intent intent = new Intent(mContext, ArticllDetailActivity.class);
                    intent.putExtra(AppConstants.ARTICLE_FROM_HOME, articleLink); // 传递链接
                    intent.putExtra(AppConstants.ARTICLE_TITLE_FROM_HOME, article.getTitle()); // 传递标题
                    mContext.startActivity(intent);
                }
            });
            holder.collectImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition = holder.getAdapterPosition();
                    Collect article = articles.get(mPosition);
                    // 发送取消收藏
                    CancelCollectPresenter mPresenter = new CancelCollectPresenter(CollectListAdapter.this, v);
                    mPresenter.cancelCollectRequest(mContext, article.getId(), article.getOriginId());
                }
            });
            return holder;
        } else if (TYPE_FOOTER == viewType) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article_footer, viewGroup, false);
            footerHolder = new FooterViewHolder(view);
            return footerHolder;
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_home_article_footer_no, viewGroup, false);
            footerHolder = new FooterViewHolder(view);
            return footerHolder;

        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_CONTENT) {
            // 正常内容
            // viewholder绑定的数据
            ArticleViewHolder articleViewHolder = (ArticleViewHolder) viewHolder;
            Collect article = articles.get(position);

            articleViewHolder.title.setText(article.getTitle());
            articleViewHolder.author.setText(article.getAuthor());
            articleViewHolder.date.setText(article.getNiceDate());
            articleViewHolder.chapterName.setText(article.getChapterName());
            articleViewHolder.superChapterName.setText("");
            // 设置收藏图片
            articleViewHolder.collectImageView.setImageResource(R.mipmap.icon_collect_select);


        } else {

        }
    }

    @Override
    public int getItemCount() {
        return articles == null ? 1 : articles.size() + 1;
    }


    /**
     * 添加内容，然后更新
     *
     * @param addArticle
     */
    public void addArticle(List<Collect> addArticle) {
        articles.addAll(addArticle);
        notifyItemRangeChanged(articles.size() - addArticle.size(), addArticle.size());
    }

    /**
     * 清空list
     */
    public void clearArticle() {
        articles.clear();
    }

    /**
     * 隐藏最后一条加载更多现实
     */
    public void deleteLastItem() {
        notifyItemRemoved(articles.size() + 1);
    }

    public void setFooterViewVisable() {
        isFooterShow = true;
    }


    public void deleteItem(int position){
        articles.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void cancelCollectSuccess(CollectData collectData) {
        if (collectData.getErrorCode() == 0){
            // 删除成功之后，清除这个item
            deleteItem(mPosition);
        }else{
            ToastUtils.toastShow(mContext, collectData.getErrorMsg());
        }
    }

    @Override
    public void cancelCollectFailed(String msg) {
        ToastUtils.toastShow(mContext, msg);
    }
}
