package com.hyn.baselibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.hyn.baselibrary.core.BaseApplication;


/**
 * 提示工具类
 * author：admin on 2015/6/2 10:41
 * mail：gychqyl@126.com
 */
public class ToastUtil {
    public static void showToast(int message) {
        Toast.makeText(BaseApplication.context(), BaseApplication.context().getString(message), Toast.LENGTH_SHORT).show();
    }
    public static void showToast(String message) {
        Toast.makeText(BaseApplication.context(), message, Toast.LENGTH_SHORT).show();
    }
}
