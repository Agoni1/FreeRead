package com.agoni.freeread.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agoni.freeread.R;
import com.agoni.freeread.activity.BangdanActivity;

/**
 * Created by Agoni on 2016/12/18.
 */

public class BangdanFragment extends Fragment {
    private View bang1,bang2,bang3,bang4,bang5,bang6,bang7;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_bangdan_layout, null);
        findView(view);
        initListener();
        return view;
    }

    private void findView(View view) {
        bang1 = view.findViewById(R.id.bang1);
        bang2 = view.findViewById(R.id.bang2);
        bang3 = view.findViewById(R.id.bang3);
        bang4 = view.findViewById(R.id.bang4);
        bang5 = view.findViewById(R.id.bang5);
        bang6 = view.findViewById(R.id.bang6);
        bang7 = view.findViewById(R.id.bang7);
    }

    private void initListener() {
        bang1.setOnClickListener(onClickListener);
        bang2.setOnClickListener(onClickListener);
        bang3.setOnClickListener(onClickListener);
        bang4.setOnClickListener(onClickListener);
        bang5.setOnClickListener(onClickListener);
        bang6.setOnClickListener(onClickListener);
        bang7.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getContext(), BangdanActivity.class);
            switch (view.getId()){
                case R.id.bang1:{
                    intent.putExtra("num",0);
                }break;
                case R.id.bang2:{
                    intent.putExtra("num",1);
                }break;
                case R.id.bang3:{
                    intent.putExtra("num",2);
                }break;
                case R.id.bang4:{
                    intent.putExtra("num",3);
                }break;
                case R.id.bang5:{
                    intent.putExtra("num",4);
                }break;
                case R.id.bang6:{
                    intent.putExtra("num",5);
                }break;
                case R.id.bang7:{
                    intent.putExtra("num",6);
                }break;
            }
            startActivity(intent);
        }
    };
}
