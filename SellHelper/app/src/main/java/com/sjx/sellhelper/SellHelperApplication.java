package com.sjx.sellhelper;

import android.app.Application;
import android.content.Context;

import com.sjx.sellhelper.constant.RunConstants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by SJX on 2017/6/20.
 * APP入口
 */

public class SellHelperApplication extends Application {

    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
        //设置可访问所有的https网站
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        //设置运行环境
        RunConstants.init(BuildConfig.ENVIRONMENT);
    }

    public static Context getContext() {
        return mApplicationContext;
    }
}
