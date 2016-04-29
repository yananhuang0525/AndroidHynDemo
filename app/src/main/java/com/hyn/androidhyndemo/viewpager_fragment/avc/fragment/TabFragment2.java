package com.hyn.androidhyndemo.viewpager_fragment.avc.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hyn.androidhyndemo.R;
import com.hyn.androidhyndemo.viewpager_fragment.api.WeatherManager;
import com.hyn.baselibrary.http.ApiCallBack;
import com.hyn.baselibrary.http.HttpHelp;
import com.hyn.baselibrary.http.HttpManager;
import com.hyn.baselibrary.http.NetCallBack;
import com.hyn.baselibrary.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyn on 2016/4/20.
 */
public class TabFragment2 extends Fragment {
    private TextView tv_result;
    private EditText et;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ac_im2, container, false);
        tv_result = (TextView) view.findViewById(R.id.tv_result);
        et = (EditText) view.findViewById(R.id.et);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().toString() != null && !et.getText().toString().equals("")) {
                    getWeather(et.getText().toString());
                } else {
                    ToastUtil.showToast("请输入要查询的城市");
                }

            }
        });
        return view;
    }

    public void getWeather(String cityName) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("加载中...");
        WeatherManager m = new WeatherManager();
        m.queryWeather(getActivity(), dialog, apiCallBack, cityName);
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
