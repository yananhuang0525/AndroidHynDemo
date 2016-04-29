package com.hyn.baselibrary.http;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by hyn on 2016/4/27.
 */
public class HttpHelp {
    public static final int defaultHttpRequestTimeout = 30000;//请求超时时间
    public class HttpErrorCode {
        public static final int NONET = -1;
        public static final int UNKNOWNERR = -2;
    }

    private static final String tag = "HttpHelp";

//    public static void post(Context context,
//                            NetCallBack netCallBack, RequestParams params,
//                            Dialog dialog, int timeout) {
//        xUtilsPost(context, netCallBack, params, dialog, timeout);
//
//    }
//
//    private static void xUtilsPost(Context context,
//                                   final NetCallBack netCallBack, RequestParams params,
//                                   final Dialog dialog, int timeout) {
//        try {
//            params.setConnectTimeout(timeout);
//            ConnectivityManager cwjManager = (ConnectivityManager) context
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            if (cwjManager.getActiveNetworkInfo() != null) // 是否可以获取网络信息
//            {
//                boolean flag = cwjManager.getActiveNetworkInfo().isAvailable();// 查看是否有活动的网络
//                Log.i(tag, "是否有活动的网络===" + flag);
//                if (!flag) {
//                    netCallBack.onFailure(new HttpException(HttpErrorCode.NONET, "对不起，暂时没有网络可以使用"));
//                    return;
//                } else {
//                    try {
//                        Log.i(tag, params.toString());
//                        x.http().post(params, new Callback.ProgressCallback<String>() {
//                            @Override
//                            public void onWaiting() {
//
//                            }
//
//                            @Override
//                            public void onStarted() {
//                                if (dialog != null) {
//                                    showDialog(dialog);
//                                }
//                                netCallBack.onStart();
//                            }
//
//                            @Override
//                            public void onLoading(long total, long current, boolean isDownloading) {
//
//                            }
//
//                            @Override
//                            public void onSuccess(String result) {
//                                Log.i(tag, "onSuccess:" + result.toString());
//                                netCallBack.onSuccess(result);
//                            }
//
//                            @Override
//                            public void onError(Throwable ex, boolean isOnCallback) {
//                                netCallBack.onFailure(new HttpException(1, ex.getMessage()));
//                            }
//
//                            @Override
//                            public void onCancelled(CancelledException cex) {
//                                if (dialog != null) {
//                                    cancelDialog(dialog);
//                                }
//                                netCallBack.onCancelled();
//                            }
//
//                            @Override
//                            public void onFinished() {
//                                if (dialog != null) {
//                                    cancelDialog(dialog);
//                                }
//                            }
//                        });
//                    } catch (Exception e) {
//                        Log.e("网络访问异常！", e.getMessage());
//                        netCallBack.onFailure(new HttpException(HttpErrorCode.UNKNOWNERR, e.getMessage()));
//                    }
//                }
//            } else {
//                netCallBack.onFailure(new HttpException(HttpErrorCode.NONET, "对不起，暂时没有网络可以使用"));
//            }
//        } catch (Exception e) {
//            Log.e("网络访问异常！", e.getMessage());
//            netCallBack.onFailure(new HttpException(HttpErrorCode.UNKNOWNERR, e.getMessage()));
//        }
//
//    }

    /**
     * 显示加载框
     *
     * @param dialog
     */
    private static void showDialog(Dialog dialog) {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 取消加载
     *
     * @param dialog
     */
    private static void cancelDialog(Dialog dialog) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * pos请求
     *
     * @param url
     * @param map
     * @param callback
     * @param <T>
     * @return
     */
    private static <T> Callback.Cancelable xUtilPost(Context context, final Dialog dialog, String url, Map<String, Object> map, final NetCallBack callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Log.i(tag, "url" + url);
        Log.i(tag, "params" + params.toString());
        Callback.Cancelable cancelable = null;
        params.setConnectTimeout(defaultHttpRequestTimeout);
        ConnectivityManager cwjManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null){
            // 是否可以获取网络信息
            boolean flag = cwjManager.getActiveNetworkInfo().isAvailable();// 查看是否有活动的网络
            Log.i(tag, "是否有活动的网络===" + flag);
            if (!flag) {
                callback.onFailure(new HttpException(HttpErrorCode.NONET, "对不起，暂时没有网络可以使用"));
            }else {
                cancelable = x.http().post(params, new Callback.ProgressCallback<String>() {
                    @Override
                    public void onWaiting() {

                    }

                    @Override
                    public void onStarted() {
                        callback.onStart();
                        if (dialog != null) {
                            showDialog(dialog);
                        }
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        callback.onSuccess(result);
                        Log.i(tag, "onSuccess:" + result.toString());
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i(tag, "onError:" + ex.getMessage());
                        callback.onFailure(new HttpException(1, ex.getMessage()));
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        callback.onCancelled();
                        if (dialog != null) {
                            cancelDialog(dialog);
                        }
                    }

                    @Override
                    public void onFinished() {
                        if (dialog != null) {
                            cancelDialog(dialog);
                        }
                    }
                });
            }
        }else {
            callback.onFailure(new HttpException(HttpErrorCode.NONET, "对不起，暂时没有网络可以使用"));
        }
        return cancelable;
    }

    /**
     * pos请求
     * @param context
     * @param dialog
     * @param apiCallBack
     * @param url
     * @param map
     */
    public static void post(Context context, Dialog dialog, final ApiCallBack apiCallBack, String url, Map<String, Object> map) {
        NetCallBack netCallBack = new NetCallBack() {
            @Override
            public void onStart() {
                apiCallBack.onStart();
            }

            @Override
            public void onCancelled() {
                apiCallBack.onCancelled();
            }

            @Override
            public void onSuccess(String result) {
                apiCallBack.onSuccessData(result);
            }

            @Override
            public void onFailure(HttpException var1) {
                apiCallBack.onFailure(var1);
            }

            @Override
            public void onCache(String data) {

            }
        };
        HttpHelp.xUtilPost(context, dialog, url, map, netCallBack);
    }
}
