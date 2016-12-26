package com.jsonliang.pay.alipay.bean;

/**
 * Created by Jsonliang on 2016/11/29.
 */

public class Query_Bean {

    /**
     * alipay_trade_query_response : {"code":"10000","msg":"Success","buyer_logon_id":"rho***@sandbox.com","buyer_pay_amount":"0.00","buyer_user_id":"2088102169529742","invoice_amount":"0.00","open_id":"20881077666376843231132861019974","out_trade_no":"1480409772691001","point_amount":"0.00","receipt_amount":"0.00","total_amount":"1.00","trade_no":"2016112921001004740200245632","trade_status":"WAIT_BUYER_PAY"}
     * sign : qS0Otfqe0yhFrISlA1OY4+tD93cCB3nM+kIJ04Cu1gIcJZERexumTRRsc3xntcMYfiCcnBNWOYkDtA4fv/R7/kAu8sirVaNIO6yXE7UAFlZlGeu8ghaUlPHbfnP8cfmh40x5CjmMV+MmifJeDbQHYo21G9wsKWcboNab70M4Rvs=
     */

    private AlipayTradeQueryResponseBean alipay_trade_query_response;
    private String sign;

    public AlipayTradeQueryResponseBean getAlipay_trade_query_response() {
        return alipay_trade_query_response;
    }

    public void setAlipay_trade_query_response(AlipayTradeQueryResponseBean alipay_trade_query_response) {
        this.alipay_trade_query_response = alipay_trade_query_response;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public static class AlipayTradeQueryResponseBean {
        /**
         * code : 10000
         * msg : Success
         * buyer_logon_id : rho***@sandbox.com
         * buyer_pay_amount : 0.00
         * buyer_user_id : 2088102169529742
         * invoice_amount : 0.00
         * open_id : 20881077666376843231132861019974
         * out_trade_no : 1480409772691001
         * point_amount : 0.00
         * receipt_amount : 0.00
         * total_amount : 1.00
         * trade_no : 2016112921001004740200245632
         * trade_status : WAIT_BUYER_PAY
         */

        private String code;
        private String msg;
        private String buyer_logon_id;
        private String buyer_pay_amount;
        private String buyer_user_id;
        private String invoice_amount;
        private String open_id;
        private String out_trade_no;
        private String point_amount;
        private String receipt_amount;
        private String total_amount;
        private String trade_no;
        private String trade_status;

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

        public String getBuyer_logon_id() {
            return buyer_logon_id;
        }

        public void setBuyer_logon_id(String buyer_logon_id) {
            this.buyer_logon_id = buyer_logon_id;
        }

        public String getBuyer_pay_amount() {
            return buyer_pay_amount;
        }

        public void setBuyer_pay_amount(String buyer_pay_amount) {
            this.buyer_pay_amount = buyer_pay_amount;
        }

        public String getBuyer_user_id() {
            return buyer_user_id;
        }

        public void setBuyer_user_id(String buyer_user_id) {
            this.buyer_user_id = buyer_user_id;
        }

        public String getInvoice_amount() {
            return invoice_amount;
        }

        public void setInvoice_amount(String invoice_amount) {
            this.invoice_amount = invoice_amount;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getPoint_amount() {
            return point_amount;
        }

        public void setPoint_amount(String point_amount) {
            this.point_amount = point_amount;
        }

        public String getReceipt_amount() {
            return receipt_amount;
        }

        public void setReceipt_amount(String receipt_amount) {
            this.receipt_amount = receipt_amount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getTrade_status() {
            return trade_status;
        }

        public void setTrade_status(String trade_status) {
            this.trade_status = trade_status;
        }
    }
}
