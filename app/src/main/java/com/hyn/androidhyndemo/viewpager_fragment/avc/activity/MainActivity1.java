package com.hyn.androidhyndemo.viewpager_fragment.avc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyn.androidhyndemo.R;
import com.hyn.androidhyndemo.viewpager_fragment.avc.adapter.MyFragmentAdapter;
import com.hyn.androidhyndemo.viewpager_fragment.avc.fragment.TabFragment1;
import com.hyn.androidhyndemo.viewpager_fragment.avc.fragment.TabFragment2;
import com.hyn.androidhyndemo.viewpager_fragment.avc.fragment.TabFragment3;
import com.hyn.androidhyndemo.viewpager_fragment.avc.fragment.TabFragment4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyn on 2016/4/15.
 */
public class MainActivity1 extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private LinearLayout ll_tab1;
    private LinearLayout ll_tab2;
    private LinearLayout ll_tab3;
    private LinearLayout ll_tab4;
    private TextView tv_name1;
    private TextView tv_name2;
    private TextView tv_name3;
    private TextView tv_name4;
    private View mTabLine;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    private ViewPager mViewPager;
    private MyFragmentAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_viewpager_fragment);
        initView();
        initTabLine();
        setTitleTextColor(0);
    }

    /**
     * 初始化控件
     */
    private void initView() {
//        ll_tab1 = (LinearLayout) findViewById(R.id.ll_tab1);
//        ll_tab2 = (LinearLayout) findViewById(R.id.ll_tab2);
//        ll_tab3 = (LinearLayout) findViewById(R.id.ll_tab3);
//        ll_tab4 = (LinearLayout) findViewById(R.id.ll_tab4);
//        ll_tab1.setOnClickListener(this);
//        ll_tab2.setOnClickListener(this);
//        ll_tab3.setOnClickListener(this);
//        ll_tab4.setOnClickListener(this);
        tv_name1 = (TextView) findViewById(R.id.tv_name1);
        tv_name2 = (TextView) findViewById(R.id.tv_name2);
        tv_name3 = (TextView) findViewById(R.id.tv_name3);
        tv_name4 = (TextView) findViewById(R.id.tv_name4);
        tv_name1.setOnClickListener(this);
        tv_name2.setOnClickListener(this);
        tv_name3.setOnClickListener(this);
        tv_name4.setOnClickListener(this);
        fragments.add(new TabFragment1());
        fragments.add(new TabFragment2());
        fragments.add(new TabFragment3());
        fragments.add(new TabFragment4());

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    /**
     * 根据屏幕的宽度，初始化引导线的宽度
     */
    private void initTabLine() {
        mTabLine =  findViewById(R.id.iv_line);
        //获取屏幕的宽度
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
        //获取控件的LayoutParams参数(注意：一定要用父控件的LayoutParams写LinearLayout.LayoutParams)
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 4;//设置该控件的layoutParams参数
        mTabLine.setLayoutParams(lp);//将修改好的layoutParams设置为该控件的layoutParams
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_name1) {
            mViewPager.setCurrentItem(0);
        } else if (id == R.id.tv_name2) {
            mViewPager.setCurrentItem(1);
        } else if (id == R.id.tv_name3) {
            mViewPager.setCurrentItem(2);
        } else if (id == R.id.tv_name4) {
            mViewPager.setCurrentItem(3);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
//        返回组件距离左侧组件的距离
        lp.leftMargin = (int) ((positionOffset + position) * screenWidth / 4);
        mTabLine.setLayoutParams(lp);
    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position);
        setTitleTextColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 设置标题文字颜色
     *
     * @param position
     */
    private void setTitleTextColor(int position) {
        if (position == 0) {
            tv_name1.setSelected(true);
            tv_name2.setSelected(false);
            tv_name3.setSelected(false);
            tv_name4.setSelected(false);
        } else if (position == 1) {
            tv_name1.setSelected(false);
            tv_name2.setSelected(true);
            tv_name3.setSelected(false);
            tv_name4.setSelected(false);
        } else if (position == 2) {
            tv_name1.setSelected(false);
            tv_name2.setSelected(false);
            tv_name3.setSelected(true);
            tv_name4.setSelected(false);
        } else if (position == 3) {
            tv_name1.setSelected(false);
            tv_name2.setSelected(false);
            tv_name3.setSelected(false);
            tv_name4.setSelected(true);
        }

    }
}
