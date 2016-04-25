package com.hyn.androidhyndemo.viewpager_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hyn.androidhyndemo.R;

import java.util.List;

/**
 * Created by hyn on 2016/4/20.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public ListViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_lv, null);
            holder = new ViewHolder();
            holder.tv_age = (TextView) view.findViewById(R.id.tv_age);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(list.get(i));
        return view;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_age;
    }
}
