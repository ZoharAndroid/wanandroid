package com.zohar.wanandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zohar.wanandroid.bean.knowledge.SubKnowledge;
import com.zohar.wanandroid.config.AppConstants;
import com.zohar.wanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zohar on 2019/8/27 16:34
 * Describe:
 */
public class KnowledgeDetailActivity extends AppCompatActivity {

    private ArrayList<SubKnowledge> mKnowledgeList;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_detail);
        title = getIntent().getStringExtra(AppConstants.KNOWLEDGE_TITLE);
        mKnowledgeList = getIntent().getParcelableArrayListExtra(AppConstants.KNOWLEDGE_CHILDREN_DATA);
        LogUtils.d(mKnowledgeList.toString());
    }
}
