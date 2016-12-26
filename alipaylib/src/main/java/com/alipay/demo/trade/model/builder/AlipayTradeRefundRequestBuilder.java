package com.alipay.demo.trade.model.builder;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

/**
 * Created by liuyangkly on 16/3/3.
 */
public class AlipayTradeRefundRequestBuilder extends RequestBuilder {
    private BizContent bizContent = new BizContent();

    @Override
    public BizContent getBizContent() {
        return bizContent;
    }

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(bizContent.outTradeNo) &&
                StringUtils.isEmpty(bizContent.tradeNo)) {
            throw new NullPointerException("out_trade_no and trade_no should not both be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.refundAmount)) {
            throw new NullPointerException("refund_amount should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.refundReason)) {
            throw new NullPointerException("refund_reson should not be NULL!");
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayTradeRefundRequestBuilder{");
        sb.append("bizContent=").append(bizContent);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AlipayTradeRefundRequestBuilder setAppAuthToken(String appAuthToken) {
        return (AlipayTradeRefundRequestBuilder) super.setAppAuthToken(appAuthToken);
    }

    @Override
    public AlipayTradeRefundRequestBuilder setNotifyUrl(String notifyUrl) {
        return (AlipayTradeRefundRequestBuilder) super.setNotifyUrl(notifyUrl);
    }

    public String getOutTradeNo() {
        return bizContent.outTradeNo;
    }

    public AlipayTradeRefundRequestBuilder setOutTradeNo(String outTradeNo) {
        bizContent.outTradeNo = outTradeNo;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setTradeNo(String tradeNo) {
        bizContent.tradeNo = tradeNo;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setRefundAmount(String refundAmount) {
        bizContent.refundAmount = refundAmount;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setOutRequestNo(String outRequestNo) {
        bizContent.outRequestNo = outRequestNo;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setRefundReason(String refundReason) {
        bizContent.refundReason = refundReason;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setStoreId(String storeId) {
        bizContent.storeId = storeId;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setAlipayStoreId(String alipayStoreId) {
        bizContent.alipayStoreId = alipayStoreId;
        return this;
    }

    public AlipayTradeRefundRequestBuilder setTerminalId(String terminalId) {
        bizContent.terminalId = terminalId;
        return this;
    }

    public String getTradeNo() {
        return bizContent.tradeNo;
    }

    public String getRefundAmount() {
        return bizContent.refundAmount;
    }

    public String getOutRequestNo() {
        return bizContent.outRequestNo;
    }

    public String getRefundReason() {
        return bizContent.refundReason;
    }

    public String getStoreId() {
        return bizContent.storeId;
    }

    public String getAlipayStoreId() {
        return bizContent.alipayStoreId;
    }

    public String getTerminalId() {
        return bizContent.terminalId;
    }

    public static class BizContent {
        @SerializedName("trade_no")
        private String tradeNo;

        @SerializedName("out_trade_no")
        private String outTradeNo;

        @SerializedName("refund_amount")
        private String refundAmount;

        @SerializedName("out_request_no")
        private String outRequestNo;

        @SerializedName("refund_reason")
        private String refundReason;

        @SerializedName("store_id")
        private String storeId;

        @SerializedName("alipay_store_id")
        private String alipayStoreId;

        @SerializedName("terminal_id")
        private String terminalId;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BizContent{");
            sb.append("tradeNo='").append(tradeNo).append('\'');
            sb.append(", outTradeNo='").append(outTradeNo).append('\'');
            sb.append(", refundAmount='").append(refundAmount).append('\'');
            sb.append(", outRequestNo='").append(outRequestNo).append('\'');
            sb.append(", refundReason='").append(refundReason).append('\'');
            sb.append(", storeId='").append(storeId).append('\'');
            sb.append(", alipayStoreId='").append(alipayStoreId).append('\'');
            sb.append(", terminalId='").append(terminalId).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
