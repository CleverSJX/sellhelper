package com.sjx.sellhelper.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sjx.sellhelper.service.FloatBallService;

/**
 * Created by SJX on 2017/6/14.
 * 开机重启服务
 */

public class BootCompletedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        FloatBallService.addBall(context);
    }
}
