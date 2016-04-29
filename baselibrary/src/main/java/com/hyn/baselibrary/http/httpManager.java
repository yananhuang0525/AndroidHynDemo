package com.hyn.baselibrary.http;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;

import java.util.Map;

/**
 * Created by hyn on 2016/4/28.
 */
public class HttpManager {
    public final int defaultHttpRequestTimeout = 30000;//请求超时时间

//    public void post(Context context, final ApiCallBack apiCallBack, RequestParams params,
//                     Dialog dialog) {
//        NetCallBack netCallBack = new NetCallBack() {
//            @Override
//            public void onStart() {
//                apiCallBack.onStart();
//            }
//
//            @Override
//            public void onCancelled() {
//                apiCallBack.onCancelled();
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                apiCallBack.onSuccessData(result);
//            }
//
//            @Override
//            public void onFailure(HttpException var1) {
//                apiCallBack.onFailure(var1);
//            }
//
//            @Override
//            public void onCache(String data) {
//
//            }
//        };
//        HttpHelp.post(context, netCallBack, params, dialog, defaultHttpRequestTimeout);
//    }


}
