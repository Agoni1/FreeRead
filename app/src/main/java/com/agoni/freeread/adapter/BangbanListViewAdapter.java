package com.agoni.freeread.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agoni.freeread.R;
import com.agoni.freeread.bean.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Agoni on 2017/2/18.
 */

public class BangbanListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Book> bookList;

    public BangbanListViewAdapter(FragmentActivity activity, List<Book> bookList) {
        this.context = activity.getApplicationContext();
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            view=View.inflate(context, R.layout.bangdan_item_layout,null);
            holder=new ViewHolder();
            holder.image= (ImageView) view.findViewById(R.id.image);
            holder.name= (TextView) view.findViewById(R.id.name);
            holder.author= (TextView) view.findViewById(R.id.author);
            holder.type= (TextView) view.findViewById(R.id.type);
            holder.desc= (TextView) view.findViewById(R.id.desc);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        Book book = bookList.get(i);
        Picasso.with(context).load("http://statics.zhuishushenqi.com"+book.cover).resize(90,120).into(holder.image);
        Log.d("tag",book.cover);
        holder.name.setText(book.title);
        holder.author.setText(book.author);
        holder.type.setText(book.cat);
        holder.desc.setText(book.shortIntro);
        return view;
    }

    class ViewHolder {
        ImageView image;
        TextView name;
        TextView author;
        TextView type;
        TextView desc;
    }
}
