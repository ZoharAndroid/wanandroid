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
 * Created by zohar on 2019/8/8 9:22
 * Describe:
 */
public class KonwlegeHierachyFragment extends Fragment {

    public static Fragment newInstance(){
        return new KonwlegeHierachyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowlege_hierachy, container, false);
        return view;
    }
}
