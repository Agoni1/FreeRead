package com.agoni.freeread.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.agoni.freeread.R;
import com.agoni.freeread.adapter.BangbanListViewAdapter;
import com.agoni.freeread.bean.Book;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agoni on 2017/1/9.
 */

public class NanbangFragment extends Fragment {
    private ListView listView;
    private TextView loading;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                String result = (String) msg.obj;
                jiexi(result);
                loading.setVisibility(View.GONE);
            }
        }
    };

    public String path;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nanbang_layout, null);
        findView(view);
        requestData(path);//获取男榜数据
        return view;
    }

    public void findView(View view) {
        listView= (ListView) view.findViewById(R.id.listview);
        loading= (TextView) view.findViewById(R.id.loading);
    }

    public void requestData(final String strUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(strUrl);
                    InputStream is = url.openStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb=new StringBuffer();
                    String line=null;
                    while ((line=reader.readLine())!=null){
                        sb.append(line);
                    }
                    Message msg = Message.obtain();
                    msg.what=1;
                    msg.obj=sb.toString().trim();
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jiexi(String result) {
//        Log.d("tag",result);
        try {
            Gson gson=new Gson();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray books = jsonObject.getJSONObject("ranking").getJSONArray("books");
            List<Book> bookList=new ArrayList<>();
            Book book;
            for (int i=0;i<books.length();i++){
                String string = books.getString(i);
                book = gson.fromJson(string, Book.class);
                bookList.add(book);
            }
            initListView(bookList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListView(List<Book> bookList) {
        BangbanListViewAdapter adapter = new BangbanListViewAdapter(getActivity(), bookList);
        listView.setAdapter(adapter);
    }

    public void setData(String id) {
        path="http://api.zhuishushenqi.com/ranking/"+id;
    }

}
