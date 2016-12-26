package com.alipay.demo.trade.model.builder;

import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by liuyangkly on 16/3/3.
 */
public class AlipayTradePrecreateRequestBuilder extends RequestBuilder {

    private BizContent bizContent = new BizContent();

    @Override
    public BizContent getBizContent() {
        return bizContent;
    }

    public boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    @Override
    public boolean validate() {
        if (isEmpty(bizContent.outTradeNo)) {
            throw new NullPointerException("out_trade_no should not be NULL!");
        }
        if (isEmpty(bizContent.totalAmount)) {
            throw new NullPointerException("total_amount should not be NULL!");
        }
        if (isEmpty(bizContent.subject)) {
            throw new NullPointerException("subject should not be NULL!");
        }
        if (isEmpty(bizContent.storeId)) {
            throw new NullPointerException("store_id should not be NULL!");
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayTradePrecreateRequestBuilder{");
        sb.append("bizContent=").append(bizContent);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AlipayTradePrecreateRequestBuilder setAppAuthToken(String appAuthToken) {
        return (AlipayTradePrecreateRequestBuilder) super.setAppAuthToken(appAuthToken);
    }

    @Override
    public AlipayTradePrecreateRequestBuilder setNotifyUrl(String notifyUrl) {
        return (AlipayTradePrecreateRequestBuilder) super.setNotifyUrl(notifyUrl);
    }

    public String getOutTradeNo() {
        return bizContent.outTradeNo;
    }

    public AlipayTradePrecreateRequestBuilder setOutTradeNo(String outTradeNo) {
        bizContent.outTradeNo = outTradeNo;
        return this;
    }

    public String getSellerId() {
        return bizContent.sellerId;
    }

    public AlipayTradePrecreateRequestBuilder setSellerId(String sellerId) {
        bizContent.sellerId = sellerId;
        return this;
    }

    public String getTotalAmount() {
        return bizContent.totalAmount;
    }

    public AlipayTradePrecreateRequestBuilder setTotalAmount(String totalAmount) {
        bizContent.totalAmount = totalAmount;
        return this;
    }

    public String getDiscountableAmount() {
        return bizContent.discountableAmount;
    }

    public AlipayTradePrecreateRequestBuilder setDiscountableAmount(String discountableAmount) {
        bizContent.discountableAmount = discountableAmount;
        return this;
    }

    public String getUndiscountableAmount() {
        return bizContent.undiscountableAmount;
    }

    public AlipayTradePrecreateRequestBuilder setUndiscountableAmount(String undiscountableAmount) {
        bizContent.undiscountableAmount = undiscountableAmount;
        return this;
    }

    public String getSubject() {
        return bizContent.subject;
    }

    public AlipayTradePrecreateRequestBuilder setSubject(String subject) {
        bizContent.subject = subject;
        return this;
    }

    public String getBody() {
        return bizContent.body;
    }

    public AlipayTradePrecreateRequestBuilder setBody(String body) {
        bizContent.body = body;
        return this;
    }

    public List<GoodsDetail> getGoodsDetailList() {
        return bizContent.goodsDetailList;
    }

    public AlipayTradePrecreateRequestBuilder setGoodsDetailList(List<GoodsDetail> goodsDetailList) {
        bizContent.goodsDetailList = goodsDetailList;
        return this;
    }

    public String getOperatorId() {
        return bizContent.operatorId;
    }

    public AlipayTradePrecreateRequestBuilder setOperatorId(String operatorId) {
        bizContent.operatorId = operatorId;
        return this;
    }

    public String getStoreId() {
        return bizContent.storeId;
    }

    public AlipayTradePrecreateRequestBuilder setStoreId(String storeId) {
        bizContent.storeId = storeId;
        return this;
    }

    public String getAlipayStoreId() {
        return bizContent.alipayStoreId;
    }

    public AlipayTradePrecreateRequestBuilder setAlipayStoreId(String alipayStoreId) {
        bizContent.alipayStoreId = alipayStoreId;
        return this;
    }

    public String getTerminalId() {
        return bizContent.terminalId;
    }

    public AlipayTradePrecreateRequestBuilder setTerminalId(String terminalId) {
        bizContent.terminalId = terminalId;
        return this;
    }

    public ExtendParams getExtendParams() {
        return bizContent.extendParams;
    }

    public AlipayTradePrecreateRequestBuilder setExtendParams(ExtendParams extendParams) {
        bizContent.extendParams = extendParams;
        return this;
    }

    public String getTimeoutExpress() {
        return bizContent.timeoutExpress;
    }

    public AlipayTradePrecreateRequestBuilder setTimeoutExpress(String timeoutExpress) {
        bizContent.timeoutExpress = timeoutExpress;
        return this;
    }

    public static class BizContent {
        @SerializedName("out_trade_no")
        private String outTradeNo;

        @SerializedName("seller_id")
        private String sellerId;

        @SerializedName("total_amount")
        private String totalAmount;

        @SerializedName("discountable_amount")
        private String discountableAmount;
        @SerializedName("undiscountable_amount")
        private String undiscountableAmount;

        private String subject;

        private String body;

        @SerializedName("goods_detail")
        private List<GoodsDetail> goodsDetailList;

        @SerializedName("operator_id")
        private String operatorId;

        @SerializedName("store_id")
        private String storeId;

        @SerializedName("alipay_store_id")
        private String alipayStoreId;

        @SerializedName("terminal_id")
        private String terminalId;

        @SerializedName("extend_params")
        private ExtendParams extendParams;

        @SerializedName("timeout_express")
        private String timeoutExpress;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BizContent{");
            sb.append("outTradeNo='").append(outTradeNo).append('\'');
            sb.append(", sellerId='").append(sellerId).append('\'');
            sb.append(", totalAmount='").append(totalAmount).append('\'');
            sb.append(", discountableAmount='").append(discountableAmount).append('\'');
            sb.append(", undiscountableAmount='").append(undiscountableAmount).append('\'');
            sb.append(", subject='").append(subject).append('\'');
            sb.append(", body='").append(body).append('\'');
            sb.append(", goodsDetailList=").append(goodsDetailList);
            sb.append(", operatorId='").append(operatorId).append('\'');
            sb.append(", storeId='").append(storeId).append('\'');
            sb.append(", alipayStoreId='").append(alipayStoreId).append('\'');
            sb.append(", terminalId='").append(terminalId).append('\'');
            sb.append(", extendParams=").append(extendParams);
            sb.append(", timeoutExpress='").append(timeoutExpress).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
