package com.hyn.baselibrary.http;

import org.xutils.ex.HttpException;

/**
 * 网络请求回调接口
 * author：admin on 2015/6/1 19:14
 * mail：gychqyl@126.com
 */
public interface NetCallBack {
    /**
     * 网络访问开始
     */
    public void onStart();

    /**
     * 网络访问取消
     */
    public void onCancelled();

    /**
     * 网络访问成功
     *
     * @param result
     */
    public void onSuccess(String result);

    /**
     * 网络访问失败
     *
     * @param var1
     */
    public void onFailure(HttpException var1);

    /**
     * 缓存返回
     *
     * @param data
     */
    public void onCache(String data);
}
