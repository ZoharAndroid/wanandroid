package com.zohar.wanandroid.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohar.wanandroid.R;

/**
 * Created by zohar on 2019/8/8 9:23
 * Describe:
 */
public class ProjectFragment extends Fragment {

    public static Fragment newInstance(){
        return new ProjectFragment();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);

        return view;


    }
}
