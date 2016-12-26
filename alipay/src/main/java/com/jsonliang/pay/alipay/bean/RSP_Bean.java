package com.jsonliang.pay.alipay.bean;

import com.alipay.api.AlipayResponse;

/**
 * Created by Jsonliang on 2016/11/29.
 */

public class RSP_Bean extends AlipayResponse {

    /**
     * alipay_trade_precreate_response : {"code":"10000","msg":"Success","out_trade_no":"1480390885915001","qr_code":"https://qr.alipay.com/bax05227lxew7t8lwiz300ec"}
     * sign : R5n+mdIECP0NXJBRAOCPYkLfQUPNojLBWyARBJvvL7LvV8VtopDwHkvoLALEVWLH/pfXRcbJeLgzrNvntZzwGS3XyaxtbA3tG/D3BRGYsIHLZnwQ3DhdUj7cFZpLJXn7RUYkO98ibpuUboCsF0Mb8BxLa/83pz691YLwtqCKOl4=
     */

    private AlipayTradePrecreateResponseBean alipay_trade_precreate_response;
    private String sign;

    public AlipayTradePrecreateResponseBean getAlipay_trade_precreate_response() {
        return alipay_trade_precreate_response;
    }

    public void setAlipay_trade_precreate_response(AlipayTradePrecreateResponseBean alipay_trade_precreate_response) {
        this.alipay_trade_precreate_response = alipay_trade_precreate_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class AlipayTradePrecreateResponseBean {
        /**
         * code : 10000
         * msg : Success
         * out_trade_no : 1480390885915001
         * qr_code : https://qr.alipay.com/bax05227lxew7t8lwiz300ec
         */

        private String code;
        private String msg;
        private String out_trade_no;
        private String qr_code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }
    }
}
