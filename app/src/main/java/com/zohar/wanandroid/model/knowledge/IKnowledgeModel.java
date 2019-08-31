package com.zohar.wanandroid.model.knowledge;

import android.content.Context;

/**
 * Created by zohar on 2019/8/27 14:08
 * Describe:
 */
public interface IKnowledgeModel {

    void sendKnowledgeGetRequest(Context context, String url, OnKnowledgeListener knowledgeListener);
}
