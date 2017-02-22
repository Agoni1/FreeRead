package com.agoni.freeread.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.agoni.freeread.R;
import com.agoni.freeread.bean.BookSource;
import com.agoni.freeread.util.NetUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadActivity extends AppCompatActivity {
    List<BookSource> sourceList;
    private String bookId;
    private ExecutorService executorService = Executors.newFixedThreadPool(5);
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String result = (String) msg.obj;
                sourceList = jiexiFreeSource(result);
                if (sourceList != null) {
                    for (int i = 0; i < sourceList.size(); i++) {
                        BookSource bookSource = sourceList.get(i);
                        if (!bookSource.name.equals("优质书源")) {
                            getAllChapter(bookSource);
                            break;
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "没有找到书源", Toast.LENGTH_SHORT).show();
                }
            }

            if (msg.what == 2) {
                String result = (String) msg.obj;
                Log.d("tag", result);

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        getBookId();
        getFreeSource(bookId);
    }

    private void getBookId() {
        bookId = getIntent().getStringExtra("id");
    }

    private void getFreeSource(final String bookId) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String path = "http://api.zhuishushenqi.com/toc?view=summary&book=" + bookId;
                NetUtil.getString(path, handler, 1);
            }
        });
    }

    private List<BookSource> jiexiFreeSource(String result) {
        try {
            JSONArray array = new JSONArray(result);
            if (array != null) {
                Gson gson = new Gson();
                List<BookSource> sourceList = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    String string = array.getString(i);
                    BookSource bookSource = gson.fromJson(string, BookSource.class);
                    sourceList.add(bookSource);
                }
                return sourceList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getAllChapter(final BookSource bookSource) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                NetUtil.getString("http://api.zhuishushenqi.com/toc/" + bookSource._id + "?view=chapters", handler, 2);
            }
        });
    }
}
