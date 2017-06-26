package com.sjx.sellhelper.constant;

/**
 * Created by SJX on 2017/6/20.
 * 运行常量类
 */

public class RunConstants {

    public static Environment runEnvironment;

    /**
     * 运行环境
     */
    public enum Environment {

        /**
         * 开发环境
         */
        DEV("收银助手测试"),
        /**
         * 生产环境
         */
        PRO("收银助手"),
        /**
         * 本地环境
         */
        LOCAL("收银助手本地");

        private String desc;

        Environment(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return this.desc;
        }
    }

    public static void init(String environment) {
        runEnvironment = Environment.valueOf(environment);
        switch (runEnvironment) {
            case DEV:
                UrlConstants.BaseApi = "http://eastapi.swao.cn/";
                break;
            case PRO:
                UrlConstants.BaseApi = "https://api.orangelife.com.cn/";
                break;
            case LOCAL:
                UrlConstants.BaseApi = "http://192.168.118.82:9020/";
                break;
            default:
                break;
        }
    }

}
