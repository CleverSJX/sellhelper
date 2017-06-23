package com.sjx.sellhelper.constant;

import okhttp3.MediaType;

/**
 * Created by SJX on 2017/6/20.
 */

public class UrlConstants {

    public static String BaseApi;

    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * 验证会员
     */
    public static final String API_AUTH_VIP = "checkPosUser";

    public static String getApi(String apiMethod) {
        return BaseApi + apiMethod;
    }
}
