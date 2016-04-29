package com.hyn.androidhyndemo.viewpager_fragment.avc.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyn.androidhyndemo.R;
import com.hyn.androidhyndemo.viewpager_fragment.avc.adapter.ListViewAdapter;
import com.hyn.baselibrary.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyn on 2016/4/20.
 */
public class TabFragment1 extends Fragment implements XListView.IXListViewListener {
    private XListView lv;
    private ListViewAdapter adapter;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ac_im111, container, false);
        initView(view);
        return view;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                lv.stopRefresh();
            }
        }
    };

    private void initView(View v) {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("---" + i);
        }
        adapter = new ListViewAdapter(getActivity(), list);
        lv = (XListView) v.findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setXListViewListener(this);
    }

    @Override
    public void onRefresh() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                lv.stopRefresh();
//            }
//        },2000);
        handler.sendEmptyMessageDelayed(0, 2000);//延时2秒停止加载
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {//延时2秒停止加载
            @Override
            public void run() {
                lv.stopLoadMore();
            }
        }, 2000);
    }
}
