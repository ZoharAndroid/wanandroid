package com.zohar.wanandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohar.wanandroid.R;
import com.zohar.wanandroid.config.AppConstants;

/**
 * Created by zohar on 2019/8/27 21:42
 * Describe:
 */
public class KnowledgeHierarchyListFragment extends Fragment {

    public static Fragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(AppConstants.ARG_PARAM1, id);
        Fragment fragment = new KnowledgeHierarchyListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge_list, container, false);
        return view;
    }
}
