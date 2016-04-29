package com.hyn.baselibrary.core;

import android.app.Application;
import android.content.Context;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by hyn on 2016/4/14.
 */
public class BaseApplication extends Application {
    protected static Context _context;  //上下文

    @Override
    public void onCreate() {
        super.onCreate();
        if (_context == null) {
            _context = getApplicationContext();
        }
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }
}
