package com.jsonliang.paydemo.zhifubao_pay.bean;

/**
 * Created by Jsonliang on 2016/11/24.
 */

public class RequestParams {
    // 基本数据
    private String service ;// , not null
    private String partner ;// 商户ID , not null
    private String _input_charset ;// 编码 GBK , not null
    private String sign_type ; // 签名方式 , not null
    private String sign;// 签名 , not null
    private String notify_url ;// 服务器异步通知页面路径 ,can null
    private String return_url ;//页面跳转同步通知页面路径 , can null
}
