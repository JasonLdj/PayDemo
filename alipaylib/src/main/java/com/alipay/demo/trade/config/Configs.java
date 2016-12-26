package com.alipay.demo.trade.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by liuyangkly on 15/6/27.
 */
public class Configs {
    private static Log log = LogFactory.getLog(Configs.class);

    private static String openApiDomain;
    private static String mcloudApiDomain;
    private static String pid;
    private static String appid;

    private static String privateKey;
    private static String publicKey;
    private static String alipayPublicKey;

    private static int maxQueryRetry;
    private static long queryDuration;

    private static int maxCancelRetry;
    private static long cancelDuration;

    private static long heartbeatDelay ;
    private static long heartbeatDuration ;
    private Configs() {
        // No Constructor
    }


    public synchronized static void init() {

        openApiDomain = "https://openapi.alipaydev.com/gateway.do";
        mcloudApiDomain = "http://mcloudmonitor.com/gateway.do";

        pid = "2088102180315995";
        appid = "2016101900725033";

        // RSA
        privateKey ="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANpcPPs+QX5sEtNzvNvIokImGS4u5YFd2y2B9KIZMnsn7PvvDxA/CQS+zcUrU3N5a5Q4HihlwDZ0TOHEqBd/IcjVol82mPlwoKVcdEslibVY42/10LRcuqNKDmZUiy6P/WEWI/+0NGktNQ/dF+0fwfH9iNIYm9eyIniaTdKTMPMlAgMBAAECgYBn96sBZS8B5qtACsdNTkYTgNhUaPfVlL6a+FlmJOEoSmZHvhdKAPlmTQLkcjCVtqZGOEIDsg7G3rEMffoa1elbOU4gJHTARy1raof+wI8n2aFH3sovqyb055D2j9fclItHIriD2tInnW+l1xmsF0zyj6iqyV71SWovcLRblGHDAQJBAPCfumXjFg48v2kYkQTPKqaZXgmDou65i0BDq5v/rJ3yPu/8X41V0moH93lv1z3YiZjdet+3GbrkHkYa+D4fWPUCQQDoUE7hibYhmM9GY22pS2O8rs3DVFVIaHb86Z/4DrQmrpgTDv4DDOfScRi6S65v/3HwFEzNgRcGtFIJFXJ2bpNxAkEA5CqR1Ys/nsB7wGxLFwRarR9AzETGexf1szp3v7TxOdQ7WdU1xg9bNq/XOo+SylJKJ2nc2cYF3/HvwwpP2phfAQJBALyp05W4R9A7EEHip4EwN3xZHcN+ro/SPqegd4h1MHqlmd0IdOM3TOELdgB9S9mqwvYtFj5ikregI2s7HqztmhECQQDUV0ui6ts6rt9RdDnSkB/p/3/faFEMUdPfU7SZmoeVj0NW2a6queXRvfS8ShL6DJBCutLrR5/9D8Hum8ER9Kd1";
        //privateKey = configs.getString("private_key");
        publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDaXDz7PkF+bBLTc7zbyKJCJhkuLuWBXdstgfSiGTJ7J+z77w8QPwkEvs3FK1NzeWuUOB4oZcA2dEzhxKgXfyHI1aJfNpj5cKClXHRLJYm1WONv9dC0XLqjSg5mVIsuj/1hFiP/tDRpLTUP3RftH8Hx/YjSGJvXsiJ4mk3SkzDzJQIDAQAB";

        //publicKey = configs.getString("public_key");
        alipayPublicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

        //alipayPublicKey = configs.getString("alipay_public_key");

        // 查询参数
        maxQueryRetry = 5;
        queryDuration = 5000;
        maxCancelRetry = 3;
        cancelDuration = 2000;

        // 交易保障调度线程
        heartbeatDelay = 5;
        heartbeatDuration = 900;

        log.info("name: " );
        //log.info(description());
    }

    public static String description() {
        StringBuilder sb = new StringBuilder("Configs{");
        sb.append("openId: ").append(openApiDomain).append("\n");
        if (StringUtils.isNotEmpty(mcloudApiDomain)) {
            sb.append(", mcloudapi: ").append(mcloudApiDomain).append("\n");
        }

        if (StringUtils.isNotEmpty(pid)) {
            sb.append(", pid: ").append(pid).append("\n");
        }
        sb.append(", appid: ").append(appid).append("\n");

        sb.append(", RSA: ").append(getKeyDescription(privateKey)).append("\n");
        sb.append(", RSA: ").append(getKeyDescription(publicKey)).append("\n");
        sb.append(", RSA: ").append(getKeyDescription(alipayPublicKey)).append("\n");

        sb.append(", : ").append(maxQueryRetry).append("\n");
        sb.append(", (): ").append(queryDuration).append("\n");
        sb.append(", : ").append(maxCancelRetry).append("\n");
        sb.append(", (): ").append(cancelDuration).append("\n");

        sb.append(", (): ").append(heartbeatDelay).append("\n");
        sb.append(", (): ").append(heartbeatDuration).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private static String getKeyDescription(String key) {
        int showLength = 6;
        if (StringUtils.isNotEmpty(key) &&
                key.length() > showLength) {
            return new StringBuilder(key.substring(0, showLength))
                    .append("******")
                    .append(key.substring(key.length() - showLength))
                    .toString();
        }
        return null;
    }


    public static String getOpenApiDomain() {
        return openApiDomain;
    }

    public static String getMcloudApiDomain() {
        return mcloudApiDomain;
    }

    public static void setMcloudApiDomain(String mcloudApiDomain) {
        Configs.mcloudApiDomain = mcloudApiDomain;
    }

    public static String getPid() {
        return pid;
    }

    public static String getAppid() {
        return appid;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public static int getMaxQueryRetry() {
        return maxQueryRetry;
    }

    public static long getQueryDuration() {
        return queryDuration;
    }

    public static int getMaxCancelRetry() {
        return maxCancelRetry;
    }

    public static long getCancelDuration() {
        return cancelDuration;
    }


    public static void setOpenApiDomain(String openApiDomain) {
        Configs.openApiDomain = openApiDomain;
    }

    public static void setPid(String pid) {
        Configs.pid = pid;
    }

    public static void setAppid(String appid) {
        Configs.appid = appid;
    }

    public static void setPrivateKey(String privateKey) {
        Configs.privateKey = privateKey;
    }

    public static void setPublicKey(String publicKey) {
        Configs.publicKey = publicKey;
    }

    public static void setAlipayPublicKey(String alipayPublicKey) {
        Configs.alipayPublicKey = alipayPublicKey;
    }

    public static void setMaxQueryRetry(int maxQueryRetry) {
        Configs.maxQueryRetry = maxQueryRetry;
    }

    public static void setQueryDuration(long queryDuration) {
        Configs.queryDuration = queryDuration;
    }

    public static void setMaxCancelRetry(int maxCancelRetry) {
        Configs.maxCancelRetry = maxCancelRetry;
    }

    public static void setCancelDuration(long cancelDuration) {
        Configs.cancelDuration = cancelDuration;
    }

    public static long getHeartbeatDelay() {
        return heartbeatDelay;
    }

    public static void setHeartbeatDelay(long heartbeatDelay) {
        Configs.heartbeatDelay = heartbeatDelay;
    }

    public static long getHeartbeatDuration() {
        return heartbeatDuration;
    }

    public static void setHeartbeatDuration(long heartbeatDuration) {
        Configs.heartbeatDuration = heartbeatDuration;
    }
}

