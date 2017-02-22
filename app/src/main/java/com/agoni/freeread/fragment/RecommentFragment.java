package com.agoni.freeread.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agoni.freeread.R;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * Created by Agoni on 2016/12/18.
 */

public class RecommentFragment extends Fragment {
    RequestQueue queue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        queue = NoHttp.newRequestQueue();
        View view = inflater.inflate(R.layout.frag_recomment_layout, null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    OnResponseListener listener = new OnResponseListener<JSONObject>() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response<JSONObject> response) {

        }

        @Override
        public void onFailed(int what, Response<JSONObject> response) {

        }

        @Override
        public void onFinish(int what) {

        }
    };
}
