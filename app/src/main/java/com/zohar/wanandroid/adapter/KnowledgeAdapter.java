package com.zohar.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zohar.wanandroid.KnowledgeDetailActivity;
import com.zohar.wanandroid.R;
import com.zohar.wanandroid.bean.knowledge.Knowledge;
import com.zohar.wanandroid.bean.knowledge.SubKnowledge;
import com.zohar.wanandroid.config.AppConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/27 15:19
 * Describe:
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<SubKnowledge> mKnowledgeDatas = new ArrayList<>();

    public KnowledgeAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 添加数据
     *
     * @param data
     */
    public void addData(List<SubKnowledge> data){
        mKnowledgeDatas.addAll(data);
        notifyItemRangeChanged(mKnowledgeDatas.size() - data.size(), data.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_knowledge, viewGroup, false);
        final KnowledgeViewHolder holder = new KnowledgeViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubKnowledge knowledge = mKnowledgeDatas.get(holder.getAdapterPosition());
                Intent intent = new Intent(mContext, KnowledgeDetailActivity.class);
                intent.putExtra(AppConstants.KNOWLEDGE_TITLE, knowledge.getName());
                intent.putParcelableArrayListExtra(AppConstants.KNOWLEDGE_CHILDREN_DATA, (ArrayList)knowledge.getChildren());
                mContext.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, int position) {
        SubKnowledge knowledge = mKnowledgeDatas.get(position);
        KnowledgeViewHolder holder = (KnowledgeViewHolder)viewHolder;
        holder.title.setText(knowledge.getName());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < knowledge.getChildren().size(); i++){
            sb.append(knowledge.getChildren().get(i).getName());
            sb.append("   ");
        }
        holder.childrenTitle.setText(sb.toString());
    }

    @Override
    public int getItemCount() {
        return mKnowledgeDatas == null ? 0 : mKnowledgeDatas.size();
    }


    private class KnowledgeViewHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView title;
        TextView childrenTitle;

        public KnowledgeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = itemView.findViewById(R.id.item_knowledge_title);
            childrenTitle = itemView.findViewById(R.id.item_text_view_content);
        }
    }

    public void clearData(){
        mKnowledgeDatas.clear();
    }

}
