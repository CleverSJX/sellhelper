package com.sjx.sellhelper.entity;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SJX on 2017/6/21.
 */

public class Vip {

    private String name;
    private String mobile;
    @SerializedName("pos_time")
    private String posTime;//二维火注册时间
    @SerializedName("createtime")
    private String createTime;//用户创建时间

    public String getName() {
        if (TextUtils.isEmpty(name)) {
            setName("****");
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosTime() {
        return posTime;
    }

    public void setPosTime(String posTime) {
        this.posTime = posTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
