package com.hyn.androidhyndemo.tab;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hyn.androidhyndemo.R;
import com.hyn.androidhyndemo.viewpager_fragment.api.WeatherManager;
import com.hyn.baselibrary.http.ApiCallBack;
import com.hyn.baselibrary.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.ex.HttpException;

/**
 * Created by hyn on 2016/4/15.
 */
public class ImActivity2 extends Activity {
    private TextView tv_result;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_im2);
        tv_result = (TextView) findViewById(R.id.tv_result);
        et = (EditText) findViewById(R.id.et);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().toString() != null && !et.getText().toString().equals("")) {
                    getWeather(et.getText().toString());
                } else {
                    ToastUtil.showToast("请输入要查询的城市");
                }

            }
        });
    }
    public void getWeather(String cityName) {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("加载中...");
        WeatherManager m = new WeatherManager();
        m.queryWeather(this, dialog, apiCallBack, cityName);
    }
    ApiCallBack<String> apiCallBack = new ApiCallBack<String>() {
        @Override
        public void onStart() {

        }

        @Override
        public void onCancelled() {

        }

        @Override
        public void onFailure(HttpException var1) {
            ToastUtil.showToast(var1.getMessage());
        }

        @Override
        public void onSuccessData(String data) {
            if (data != null) {
                try {
                    JSONObject object = new JSONObject(data);
                    String m = object.getString("errNum");
                    if (m.equals("0")) {
                        JSONObject jsonObject = object.getJSONObject("retData");
                        String r = "城市：" + jsonObject.getString("city") + "\n" + "天气：" + jsonObject.getString("weather") + "\n" + "风向：" + jsonObject.getString("WD") + "\n" + "温度：" + jsonObject.getString("temp") + "\n" + "更新时间：" + jsonObject.getString("date") + "   " + jsonObject.getString("time") + "\n";
                        tv_result.setText(r);
                    } else {
                        ToastUtil.showToast(object.getString("errMsg"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onNoData(String data) {

        }

        @Override
        public void onErrorData(String data) {

        }
    };
}
