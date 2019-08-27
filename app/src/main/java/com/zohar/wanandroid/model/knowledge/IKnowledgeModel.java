package com.zohar.wanandroid.model.knowledge;

/**
 * Created by zohar on 2019/8/27 14:08
 * Describe:
 */
public interface IKnowledgeModel {

    void sendKnowledgeGetRequest(String url, OnKnowledgeListener knowledgeListener);
}
