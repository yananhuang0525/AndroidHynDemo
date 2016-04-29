package com.hyn.androidhyndemo.viewpager_fragment.api;

import android.app.Dialog;
import android.content.Context;

import com.hyn.baselibrary.http.ApiCallBack;
import com.hyn.baselibrary.http.HttpHelp;
import com.hyn.baselibrary.http.HttpManager;

import org.xutils.http.RequestParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyn on 2016/4/29.
 */
public class WeatherManager {
    /**
     * 查询天气
     *
     * @param context
     * @param dialog
     * @param apiCallBack
     * @param cityName
     */
    public void queryWeather(Context context, Dialog dialog, ApiCallBack apiCallBack, String cityName) {
        String url = "http://apistore.baidu.com/microservice/weather?";
        RequestParams params = new RequestParams(url);
        params.addParameter("cityname", cityName);
        Map<String, Object> map = new HashMap<>();
        map.put("cityname", cityName);
        HttpHelp.post(context, dialog, apiCallBack, url, map);
    }
}
