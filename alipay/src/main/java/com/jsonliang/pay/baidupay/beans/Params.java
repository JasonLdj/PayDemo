package com.jsonliang.pay.baidupay.beans;

/**
 * Created by Jsonliang on 2016/11/30.
 */

public class Params {
    private String code_type; // 码大小 (不参与签名 )
    private String code_size; //输出格式 (不参与签名 )
    private String output_type; //不带百度钱包 LOGOLOGOLOGOLOGO(不参与签名 )
    private String nologo; //不带百度钱包LOGO(不参与签名 )
    private String mno; //实体商户门店号 (不参与签名 )
    private String mname; //实体商户门店名称(不参与签名)
    private String tno; //实体商户终端号(不参与签名 )

    // 收银台参数
    private String service_code;// 服务编号
    private String sp_no; //百付宝商户号
    private String order_create_time;// 订单生成时间 YYYYMMDDHHMMSS
    private String order_no; // 订单号
    private String goods_name;// 商品名称
    private String goods_desc;// 商品的描述信息
    private String goods_url;//商品在户网站上的
    private String unit_amount;//商品单价，以分为单位
    private String unit_count;// 商品数量
    private String transport_amount;// 运费
    private String total_amount; // 商品总金额，以分为单位
    private String currency;// 币种，默认为人民币
    private String buyer_sp_username;// 买家在商户网站的用户名
    private String return_url;//百付宝主动通知商户支付结果URL
    private String page_url;// 用户点击该URL可以返回到商户网站；该URL可以起到通知支付结果的作用
    private String pay_type;// 默认支付方式
    private String bank_no;// 网银支付或银行网关支付时，默认的银行编码
    private String expire_time;// 交易超时时间
    private String sp_uno;// 用户在商户端的用户ID
    private String input_charset;// 请求参数字符编码
    private String version; // 接口版本号
    private String sign;// 签名结果
    private String sign_method; // 签名方法
    private String extra; // 商户自定义数据
    private String profit_type;// 分润类型
    private String profit_solution;// 分润方案
    private String sp_pass_through; //商户制定服务字段


    public String getCode_type() {
        return code_type;
    }

    public void setCode_type(String code_type) {
        this.code_type = code_type;
    }

    public String getCode_size() {
        return code_size;
    }

    public void setCode_size(String code_size) {
        this.code_size = code_size;
    }

    public String getOutput_type() {
        return output_type;
    }

    public void setOutput_type(String output_type) {
        this.output_type = output_type;
    }

    public String getNologo() {
        return nologo;
    }

    public void setNologo(String nologo) {
        this.nologo = nologo;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getService_code() {
        return service_code;
    }

    public void setService_code(String service_code) {
        this.service_code = service_code;
    }

    public String getSp_no() {
        return sp_no;
    }

    public void setSp_no(String sp_no) {
        this.sp_no = sp_no;
    }

    public String getOrder_create_time() {
        return order_create_time;
    }

    public void setOrder_create_time(String order_create_time) {
        this.order_create_time = order_create_time;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getGoods_url() {
        return goods_url;
    }

    public void setGoods_url(String goods_url) {
        this.goods_url = goods_url;
    }

    public String getUnit_amount() {
        return unit_amount;
    }

    public void setUnit_amount(String unit_amount) {
        this.unit_amount = unit_amount;
    }

    public String getUnit_count() {
        return unit_count;
    }

    public void setUnit_count(String unit_count) {
        this.unit_count = unit_count;
    }

    public String getTransport_amount() {
        return transport_amount;
    }

    public void setTransport_amount(String transport_amount) {
        this.transport_amount = transport_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBuyer_sp_username() {
        return buyer_sp_username;
    }

    public void setBuyer_sp_username(String buyer_sp_username) {
        this.buyer_sp_username = buyer_sp_username;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getPage_url() {
        return page_url;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(String expire_time) {
        this.expire_time = expire_time;
    }

    public String getSp_uno() {
        return sp_uno;
    }

    public void setSp_uno(String sp_uno) {
        this.sp_uno = sp_uno;
    }

    public String getInput_charset() {
        return input_charset;
    }

    public void setInput_charset(String input_charset) {
        this.input_charset = input_charset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_method() {
        return sign_method;
    }

    public void setSign_method(String sign_method) {
        this.sign_method = sign_method;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getProfit_type() {
        return profit_type;
    }

    public void setProfit_type(String profit_type) {
        this.profit_type = profit_type;
    }

    public String getProfit_solution() {
        return profit_solution;
    }

    public void setProfit_solution(String profit_solution) {
        this.profit_solution = profit_solution;
    }

    public String getSp_pass_through() {
        return sp_pass_through;
    }

    public void setSp_pass_through(String sp_pass_through) {
        this.sp_pass_through = sp_pass_through;
    }
}