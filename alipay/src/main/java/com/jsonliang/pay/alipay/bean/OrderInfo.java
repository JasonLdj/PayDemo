package com.jsonliang.pay.alipay.bean;

import java.util.List;

/**
 * Created by Jsonliang on 2016/11/24.
 */

public class OrderInfo {
    /**
     * out_trade_no : 20150320010101001
     * seller_id : 2088102146225135
     * total_amount : 88.88
     * discountable_amount : 8.88
     * undiscountable_amount : 80
     * buyer_logon_id : 15901825620
     * subject : Iphone6 16G
     * body : Iphone6 16G
     * goods_detail : [{"goods_id":"apple-01","alipay_goods_id":"20010001","goods_name":"ipad","quantity":1,"price":2000,"goods_category":"34543238","body":"特价手机","show_url":"http://www.alipay.com/xxx.jpg"}]
     * operator_id : yx_001
     * store_id : NJ_001
     * terminal_id : NJ_T_001
     * extend_params : {"sys_service_provider_id":"2088511833207846","hb_fq_num":"3","hb_fq_seller_percent":"100"}
     * timeout_express : 90m
     * royalty_info : {"royalty_type":"ROYALTY","royalty_detail_infos":[{"serial_no":1,"trans_in_type":"userId","batch_no":"123","out_relation_id":"20131124001","trans_out_type":"userId","trans_out":"2088101126765726","trans_in":"2088101126708402","amount":0.1,"desc":"分账测试1","amount_percentage":"100"}]}
     * sub_merchant : {"merchant_id":"19023454"}
     * alipay_store_id : 2016052600077000000015640104
     */

    private String out_trade_no; // 必填 d订单号
    private String seller_id;
    private String total_amount; // 必填 总金额
    private String discountable_amount;
    private String undiscountable_amount;
    private String buyer_logon_id;
    private String subject; // 必填 标题
    private String body;
    private String operator_id;
    private String store_id;
    private String terminal_id;
    private ExtendParamsBean extend_params;
    private String timeout_express;
    private RoyaltyInfoBean royalty_info;
    private SubMerchantBean sub_merchant;
    private String alipay_store_id; // 支付宝店铺的门店ID
    private List<GoodsDetailBean> goods_detail;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getDiscountable_amount() {
        return discountable_amount;
    }

    public void setDiscountable_amount(String discountable_amount) {
        this.discountable_amount = discountable_amount;
    }

    public String getUndiscountable_amount() {
        return undiscountable_amount;
    }

    public void setUndiscountable_amount(String undiscountable_amount) {
        this.undiscountable_amount = undiscountable_amount;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public void setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public ExtendParamsBean getExtend_params() {
        return extend_params;
    }

    public void setExtend_params(ExtendParamsBean extend_params) {
        this.extend_params = extend_params;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public RoyaltyInfoBean getRoyalty_info() {
        return royalty_info;
    }

    public void setRoyalty_info(RoyaltyInfoBean royalty_info) {
        this.royalty_info = royalty_info;
    }

    public SubMerchantBean getSub_merchant() {
        return sub_merchant;
    }

    public void setSub_merchant(SubMerchantBean sub_merchant) {
        this.sub_merchant = sub_merchant;
    }

    public String getAlipay_store_id() {
        return alipay_store_id;
    }

    public void setAlipay_store_id(String alipay_store_id) {
        this.alipay_store_id = alipay_store_id;
    }

    public List<GoodsDetailBean> getGoods_detail() {
        return goods_detail;
    }

    public void setGoods_detail(List<GoodsDetailBean> goods_detail) {
        this.goods_detail = goods_detail;
    }

    public static class ExtendParamsBean {
        /**
         * sys_service_provider_id : 2088511833207846
         * hb_fq_num : 3
         * hb_fq_seller_percent : 100
         */

        private String sys_service_provider_id;
        private String hb_fq_num;
        private String hb_fq_seller_percent;

        public String getSys_service_provider_id() {
            return sys_service_provider_id;
        }

        public void setSys_service_provider_id(String sys_service_provider_id) {
            this.sys_service_provider_id = sys_service_provider_id;
        }

        public String getHb_fq_num() {
            return hb_fq_num;
        }

        public void setHb_fq_num(String hb_fq_num) {
            this.hb_fq_num = hb_fq_num;
        }

        public String getHb_fq_seller_percent() {
            return hb_fq_seller_percent;
        }

        public void setHb_fq_seller_percent(String hb_fq_seller_percent) {
            this.hb_fq_seller_percent = hb_fq_seller_percent;
        }
    }

    public static class RoyaltyInfoBean {
        /**
         * royalty_type : ROYALTY
         * royalty_detail_infos : [{"serial_no":1,"trans_in_type":"userId","batch_no":"123","out_relation_id":"20131124001","trans_out_type":"userId","trans_out":"2088101126765726","trans_in":"2088101126708402","amount":0.1,"desc":"分账测试1","amount_percentage":"100"}]
         */

        private String royalty_type;
        private List<RoyaltyDetailInfosBean> royalty_detail_infos;

        public String getRoyalty_type() {
            return royalty_type;
        }

        public void setRoyalty_type(String royalty_type) {
            this.royalty_type = royalty_type;
        }

        public List<RoyaltyDetailInfosBean> getRoyalty_detail_infos() {
            return royalty_detail_infos;
        }

        public void setRoyalty_detail_infos(List<RoyaltyDetailInfosBean> royalty_detail_infos) {
            this.royalty_detail_infos = royalty_detail_infos;
        }

        public static class RoyaltyDetailInfosBean {
            /**
             * serial_no : 1
             * trans_in_type : userId
             * batch_no : 123
             * out_relation_id : 20131124001
             * trans_out_type : userId
             * trans_out : 2088101126765726
             * trans_in : 2088101126708402
             * amount : 0.1
             * desc : 分账测试1
             * amount_percentage : 100
             */

            private int serial_no;
            private String trans_in_type;
            private String batch_no;
            private String out_relation_id;
            private String trans_out_type;
            private String trans_out;
            private String trans_in;
            private double amount;
            private String desc;
            private String amount_percentage;

            public int getSerial_no() {
                return serial_no;
            }

            public void setSerial_no(int serial_no) {
                this.serial_no = serial_no;
            }

            public String getTrans_in_type() {
                return trans_in_type;
            }

            public void setTrans_in_type(String trans_in_type) {
                this.trans_in_type = trans_in_type;
            }

            public String getBatch_no() {
                return batch_no;
            }

            public void setBatch_no(String batch_no) {
                this.batch_no = batch_no;
            }

            public String getOut_relation_id() {
                return out_relation_id;
            }

            public void setOut_relation_id(String out_relation_id) {
                this.out_relation_id = out_relation_id;
            }

            public String getTrans_out_type() {
                return trans_out_type;
            }

            public void setTrans_out_type(String trans_out_type) {
                this.trans_out_type = trans_out_type;
            }

            public String getTrans_out() {
                return trans_out;
            }

            public void setTrans_out(String trans_out) {
                this.trans_out = trans_out;
            }

            public String getTrans_in() {
                return trans_in;
            }

            public void setTrans_in(String trans_in) {
                this.trans_in = trans_in;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getAmount_percentage() {
                return amount_percentage;
            }

            public void setAmount_percentage(String amount_percentage) {
                this.amount_percentage = amount_percentage;
            }
        }
    }

    public static class SubMerchantBean {
        /**
         * merchant_id : 19023454
         */

        private String merchant_id;

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }
    }

    public static class GoodsDetailBean {
        /**
         * goods_id : apple-01
         * alipay_goods_id : 20010001
         * goods_name : ipad
         * quantity : 1
         * price : 2000
         * goods_category : 34543238
         * body : 特价手机
         * show_url : http://www.alipay.com/xxx.jpg
         */

        private String goods_id;
        private String alipay_goods_id;
        private String goods_name;
        private int quantity;
        private int price;
        private String goods_category;
        private String body;
        private String show_url;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getAlipay_goods_id() {
            return alipay_goods_id;
        }

        public void setAlipay_goods_id(String alipay_goods_id) {
            this.alipay_goods_id = alipay_goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getGoods_category() {
            return goods_category;
        }

        public void setGoods_category(String goods_category) {
            this.goods_category = goods_category;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getShow_url() {
            return show_url;
        }

        public void setShow_url(String show_url) {
            this.show_url = show_url;
        }
    }
}
