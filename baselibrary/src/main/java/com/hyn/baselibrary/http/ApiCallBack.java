package com.hyn.baselibrary.http;

import org.xutils.ex.HttpException;

/**
 * Des:
 * User: anxing(13683717560@139.com)
 * Date: 2015-06-02
 * Time: 17:30
 * FIXME
 */
public interface ApiCallBack<T> {
    /**
     * 网络访问开始
     */
    public void onStart();

    /**
     * 网络访问取消
     */
    public void onCancelled() ;

    /**
     * 网络访问失败
     * @param var1
     */
    public  void onFailure(HttpException var1);

    /**
     *网络访问成功
     * @param data
     */
    public  void onSuccessData(T data);
    /*
    无数据，这个是为了兼容目前的，远端返回无数据
     */
    public  void onNoData(T data);

    /**
     * 这个远端返回错误码
     * @param data
     */
    public  void onErrorData(T data);

}
