package com.example.downloadimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    ArrayList<Photo> data;
    Context context;

    public Adapter(ArrayList<Photo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView v;
        if(view == null)
        {
            v = new MyView();
            view = inflater.inflate(R.layout.item_layout, null);
            v.imageView = view.findViewById(R.id.imageView);
            v.title = view.findViewById(R.id.title);
            view.setTag(v);
        }
        else{
            v = (MyView) view.getTag();
        }
        //new DownloadImage(v.imageView).execute(data.get(i).getSource());
        Picasso.get().load(data.get(i).getSource()).into(v.imageView);
        v.title.setText(data.get(i).getTitle());
        return view;
    }
    private static class MyView
    {
        ImageView imageView;
        TextView title;
    }
}
