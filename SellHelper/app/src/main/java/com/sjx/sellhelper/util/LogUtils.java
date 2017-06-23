package com.sjx.sellhelper.util;

import android.util.Log;

import com.sjx.sellhelper.BuildConfig;

/**
 * Created by SJX on 2017/6/23.
 */

public class LogUtils {

    public static final boolean SHOW_LOG = BuildConfig.LOG_DEBUG;

    public static void v(String tag, String message) {
        if (SHOW_LOG)
            Log.v(tag, message);
    }

    public static void d(String tag, String message) {
        if (SHOW_LOG)
            Log.d(tag, message);
    }

    public static void i(String tag, String message) {
        if (SHOW_LOG)
            Log.i(tag, message);
    }

    public static void w(String tag, String message) {
        if (SHOW_LOG)
            Log.w(tag, message);
    }

    public static void e(String tag, String message) {
        if (SHOW_LOG)
            Log.e(tag, message);
    }

    public static void e(String tag, Throwable throwable) {
        if (SHOW_LOG)
            e(tag, null, throwable);
    }

    public static void e(String tag, String message, Throwable throwable) {
        if (SHOW_LOG)
            Log.e(tag, message, throwable);
    }

}
