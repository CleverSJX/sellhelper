package com.sjx.sellhelper.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;

import com.sjx.sellhelper.view.FloatWindowManager;

/**
 * Created by SJX on 2017/6/20.
 * 悬浮球服务
 */

public class FloatBallService extends AccessibilityService {
    public static final int TYPE_ADD = 0;
    public static final int TYPE_DEL = 1;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }

    public static void addBall(Context context) {
        Intent intent = new Intent(context, FloatBallService.class);
        Bundle data = new Bundle();
        data.putInt("type", FloatBallService.TYPE_ADD);
        intent.putExtras(data);
        context.startService(intent);
    }

    public static void deleteBall(Context context) {
        Intent intent = new Intent(context, FloatBallService.class);
        Bundle data = new Bundle();
        data.putInt("type", FloatBallService.TYPE_DEL);
        intent.putExtras(data);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle data = intent.getExtras();
        if (data != null) {
            int type = data.getInt("type");
            if (type == TYPE_ADD) {
                FloatWindowManager.addBallView(this);
            } else {
                FloatWindowManager.removeBallView(this);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
