package com.hyn.androidhyndemo.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.hyn.androidhyndemo.R;

public class MainActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener {
    private TabHost mHost; // 菜单切换
    private RadioGroup group; // 菜单按钮
    private RadioButton radio_im;
    private RadioButton radio_im2;
    private RadioButton radio_im3;
    private RadioButton radio_im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {
        mHost = getTabHost();
        mHost.addTab(mHost.newTabSpec("IM").setIndicator("IM")
                .setContent(new Intent(this, ImActivity.class)));
        mHost.addTab(mHost.newTabSpec("IM2").setIndicator("IM2")
                .setContent(new Intent(this, ImActivity2.class)));
        mHost.addTab(mHost.newTabSpec("IM3").setIndicator("IM3")
                .setContent(new Intent(this, ImActivity3.class)));
        mHost.addTab(mHost.newTabSpec("IM4").setIndicator("IM4")
                .setContent(new Intent(this, ImActivity4.class)));
        group = (RadioGroup) findViewById(R.id.main_radio);
        group.setOnCheckedChangeListener(this);
        radio_im = (RadioButton) findViewById(R.id.radio_im);
        radio_im2 = (RadioButton) findViewById(R.id.radio_im2);
        radio_im3 = (RadioButton) findViewById(R.id.radio_im3);
        radio_im4 = (RadioButton) findViewById(R.id.radio_im4);
        group.check(R.id.radio_im);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio_im:
                mHost.setCurrentTabByTag("IM");
                break;
            case R.id.radio_im2:
                mHost.setCurrentTabByTag("IM2");
                break;
            case R.id.radio_im3:
                mHost.setCurrentTabByTag("IM3");
                break;
            case R.id.radio_im4:
                mHost.setCurrentTabByTag("IM4");
                break;
        }
    }
}
