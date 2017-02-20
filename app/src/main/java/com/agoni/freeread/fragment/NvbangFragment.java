package com.agoni.freeread.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agoni.freeread.R;

/**
 * Created by Agoni on 2017/1/9.
 */

public class NvbangFragment extends NanbangFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nvbang_layout, null);
        super.findView(view);
        super.requestData(path);//获取女榜数据
        return view;
    }
}
