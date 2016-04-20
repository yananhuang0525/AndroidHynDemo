package com.hyn.androidhyndemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hyn.androidhyndemo.tab.MainActivity;
import com.hyn.androidhyndemo.viewpager_fragment.MainActivity1;

/**
 * Created by hyn on 2016/4/20.
 */
public class Main extends Activity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn1) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (id == R.id.btn2) {
            startActivity(new Intent(this, MainActivity1.class));
        }
    }
}
