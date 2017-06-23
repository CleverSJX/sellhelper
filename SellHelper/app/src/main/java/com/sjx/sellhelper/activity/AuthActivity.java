package com.sjx.sellhelper.activity;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sjx.sellhelper.R;
import com.sjx.sellhelper.constant.UrlConstants;
import com.sjx.sellhelper.entity.Vip;
import com.sjx.sellhelper.util.JSONUtils;
import com.sjx.sellhelper.util.LogUtils;
import com.sjx.sellhelper.util.UIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.sjx.sellhelper.constant.UrlConstants.API_AUTH_VIP;

public class AuthActivity extends BaseActivity {
    private static final String TAG = "AuthActivity";

    @BindView(R.id.et_vip)
    EditText etVip;
    @BindView(R.id.btn_auth)
    Button btnAuth;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_pos_time)
    TextView tvPosTime;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;

    ProgressDialog mProgressDialog;
    @BindView(R.id.btn_warn_confirm)
    Button btnWarnConfirm;
    @BindView(R.id.ll_warn)
    LinearLayout llWarn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        createProgressDialog();
    }

    private void createProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("验证");
        mProgressDialog.setMessage("查询中...");
    }

    private void showInfo(String mobile) {
        mProgressDialog.show();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile", mobile);
        OkHttpUtils.postString()
                .url(UrlConstants.getApi(API_AUTH_VIP))
                .content(jsonObject.toString())
                .mediaType(UrlConstants.JSON_TYPE)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        mProgressDialog.dismiss();
                        LogUtils.e(TAG, "网络异常", e);
                        Toast.makeText(AuthActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mProgressDialog.dismiss();
                        JsonObject result = JSONUtils.parse(response);
                        if (JSONUtils.getElementInt(result, "code") == 0) {
                            //正常数据返回
                            JsonObject data = JSONUtils.getElementJsonObject(result, "data");
                            if (JSONUtils.getElementBoolean(data, "isMember")) {
                                //是会员
                                //显示详情
                                llInfo.setVisibility(View.VISIBLE);
                                llWarn.setVisibility(View.GONE);
                                Vip vip = JSONUtils.fromJsonObject(data, Vip.class);
                                tvMobile.setText(vip.getMobile());
                                tvName.setText(vip.getName());
                                tvPosTime.setText(vip.getPosTime());
                                tvCreateTime.setText(vip.getCreateTime());
                            } else {
                                llWarn.setVisibility(View.VISIBLE);
                                llInfo.setVisibility(View.GONE);
                            }
                        } else {
                            LogUtils.e(TAG, "系统异常" + result.toString());
                            Toast.makeText(AuthActivity.this, "系统异常", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    /**
     * 是否在前台显示
     */
    public static boolean isForeground = false;

    @Override
    protected void onResume() {
        super.onResume();
        isForeground = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, 0, intent, 0);
        try {
            pendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_auth, R.id.btn_confirm, R.id.btn_warn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_auth:
                String mobile = etVip.getText().toString().trim();
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(this, "请输入会员号", Toast.LENGTH_SHORT).show();
                } else {
                    UIUtils.hideKeyboard(this);
                    showInfo(mobile);
                }
                break;
            case R.id.btn_confirm:
            case R.id.btn_warn_confirm:
                finish();
                break;
        }
    }
}
