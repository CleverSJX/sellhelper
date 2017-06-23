package com.sjx.sellhelper.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sjx.sellhelper.R;
import com.sjx.sellhelper.constant.RunConstants;
import com.sjx.sellhelper.service.FloatBallService;
import com.sjx.sellhelper.util.AccessibilityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_quit)
    Button btnQuit;
    @BindView(R.id.tv_environment)
    TextView tvEnvironment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvEnvironment.setText(RunConstants.runEnvironment.getDesc());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 1);
                Toast.makeText(this, "请先允许收银助手出现在顶部", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void checkAccessibility() {
        // 判断辅助功能是否开启
        if (!AccessibilityUtil.isAccessibilitySettingsOn(this)) {
            // 引导至辅助功能设置页面
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            Toast.makeText(this, "请先开启收银助手辅助功能", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.btn_start, R.id.btn_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                checkAccessibility();
                FloatBallService.addBall(this);
                finish();
                break;
            case R.id.btn_quit:
                FloatBallService.deleteBall(this);
                finish();
                break;
        }
    }
}
