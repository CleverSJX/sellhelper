package com.sjx.sellhelper.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sjx.sellhelper.util.UIUtils;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UIUtils.setTransparentStatus(this);
    }
}
