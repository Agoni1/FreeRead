package com.agoni.freeread.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agoni.freeread.R;
import com.agoni.freeread.bean.Book;
import com.agoni.freeread.bean.BookDetail;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class BookDetailActivity extends SwipeBackActivity {
    private ImageView image;
    private TextView name,author,type,cat,lastChapter,lastTime,desc,read,add;
    private LinearLayout ll;

    private BookDetail bookDetail;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                jiexi((String) msg.obj);
                requestOtherBook(bookDetail.author);
                initView(bookDetail);
            }
            if (msg.what==2){
                String result = (String) msg.obj;
                jiexiOtherBook(result);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_dail);
        findView();
        String path = getPath();
        Log.d("tag",path);
        requestData(path);
    }

    private void requestData(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    InputStream is = null;
                    is = url.openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.obj = sb.toString().trim();
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void requestOtherBook(final String author) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String en = URLEncoder.encode(author);
                    URL url = new URL("http://api.zhuishushenqi.com/book/fuzzy-search?query="+en);
                    InputStream is = null;
                    is = url.openStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer sb = new StringBuffer();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    Message msg = Message.obtain();
                    msg.what = 2;
                    msg.obj = sb.toString().trim();
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jiexi(String result) {
        Gson gson = new Gson();
         bookDetail = gson.fromJson(result, BookDetail.class);
    }

    private void jiexiOtherBook(String result) {
        try {
            JSONObject object = new JSONObject(result);
            JSONArray books = object.getJSONArray("books");
            Gson gson=new Gson();
            List<Book> bookList=new ArrayList<>();
            for (int i=0;i<books.length();i++){
                if (books.getJSONObject(i).getString("author").equals(bookDetail.author)){
                    Book book = gson.fromJson(books.getString(i), Book.class);
                    bookList.add(book);
                }else {
                    break;
                }
            }
            if (bookList.size()>0){
                loadOtherBook(bookList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getPath() {
       return "http://api.zhuishushenqi.com/book/"+ getIntent().getStringExtra("id");
    }

    private void findView() {
        image= (ImageView) findViewById(R.id.image);
        name= (TextView) findViewById(R.id.name);
        author= (TextView) findViewById(R.id.author);
        type= (TextView) findViewById(R.id.type);
        cat= (TextView) findViewById(R.id.cat);
        lastChapter= (TextView) findViewById(R.id.lastChapter);
        lastTime= (TextView) findViewById(R.id.lastTime);
        desc= (TextView) findViewById(R.id.desc);
        read= (TextView) findViewById(R.id.read);
        add= (TextView) findViewById(R.id.add);
        ll= (LinearLayout) findViewById(R.id.rec_ll);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
                intent.putExtra("id",bookDetail._id);
                startActivity(intent);
            }
        });
    }

    private void initView(BookDetail bookDetail) {
        Picasso.with(this).load("http://statics.zhuishushenqi.com"+bookDetail.cover).resize(400,500).into(image);
        name.setText(bookDetail.title);
        author.setText(bookDetail.author);
        type.setText(bookDetail.majorCate);
        cat.setText(bookDetail.minorCate);
        lastChapter.setText(bookDetail.lastChapter);
        lastTime.setText("更新时间: "+parseTime(bookDetail.updated));
        desc.setText(bookDetail.longIntro);
    }

    private String parseTime(String time) {
        time = time.replaceFirst("-", "年");
        time = time.replaceFirst("-","月");
        time = time.replaceFirst("T","日");
        time = time.substring(0,time.lastIndexOf("."));
        return time;
    }

    private void loadOtherBook(List<Book> bookList) {
        for (int i=0;i<bookList.size();i++){
            final Book book = bookList.get(i);
            View view = View.inflate(this, R.layout.recbook_layout, null);
            ImageView image = (ImageView) view.findViewById(R.id.recimage);
            TextView name= (TextView) view.findViewById(R.id.recname);
            Picasso.with(this).load("http://statics.zhuishushenqi.com"+book.cover).resize(100,120).into(image);
            name.setText(book.title);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),book.title,Toast.LENGTH_SHORT).show();
                }
            });
            ll.addView(view);
        }

    }
}
