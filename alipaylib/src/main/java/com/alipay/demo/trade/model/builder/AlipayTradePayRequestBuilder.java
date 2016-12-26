package com.alipay.demo.trade.model.builder;

import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by liuyangkly on 16/3/3.
 */
public class AlipayTradePayRequestBuilder extends RequestBuilder {

    private BizContent bizContent = new BizContent();

    @Override
    public BizContent getBizContent() {
        return bizContent;
    }

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(bizContent.scene)) {
            throw new NullPointerException("scene should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.authCode)) {
            throw new NullPointerException("auth_code should not be NULL!");
        }
        if (!Pattern.matches("^\\d{10,}$", bizContent.authCode)) {
            throw new IllegalStateException("invalid auth_code!");
        }
        if (StringUtils.isEmpty(bizContent.outTradeNo)) {
            throw new NullPointerException("out_trade_no should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.totalAmount)) {
            throw new NullPointerException("total_amount should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.subject)) {
            throw new NullPointerException("subject should not be NULL!");
        }
        if (StringUtils.isEmpty(bizContent.storeId)) {
            throw new NullPointerException("store_id should not be NULL!");
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayTradePayRequestBuilder{");
        sb.append("bizContent=").append(bizContent);
        sb.append(", super=").append(super.toString());
        sb.append('}');
        return sb.toString();
    }

    public AlipayTradePayRequestBuilder() {
        // 条码支付，场景为bar_code
        bizContent.scene = "bar_code";
    }

    @Override
    public AlipayTradePayRequestBuilder setAppAuthToken(String appAuthToken) {
        return (AlipayTradePayRequestBuilder) super.setAppAuthToken(appAuthToken);
    }

    @Override
    public AlipayTradePayRequestBuilder setNotifyUrl(String notifyUrl) {
        return (AlipayTradePayRequestBuilder) super.setNotifyUrl(notifyUrl);
    }

    public String getScene() {
        return bizContent.scene;
    }

    public AlipayTradePayRequestBuilder setScene(String scene) {
        bizContent.scene = scene;
        return this;
    }

    public String getAuthCode() {
        return bizContent.authCode;
    }

    public AlipayTradePayRequestBuilder setAuthCode(String authCode) {
        bizContent.authCode = authCode;
        return this;
    }

    public String getOutTradeNo() {
        return bizContent.outTradeNo;
    }

    public AlipayTradePayRequestBuilder setOutTradeNo(String outTradeNo) {
        bizContent.outTradeNo = outTradeNo;
        return this;
    }

    public String getSellerId() {
        return bizContent.sellerId;
    }

    public AlipayTradePayRequestBuilder setSellerId(String sellerId) {
        bizContent.sellerId = sellerId;
        return this;
    }

    public String getTotalAmount() {
        return bizContent.totalAmount;
    }

    public AlipayTradePayRequestBuilder setTotalAmount(String totalAmount) {
        bizContent.totalAmount = totalAmount;
        return this;
    }

    public String getDiscountableAmount() {
        return bizContent.discountableAmount;
    }

    public AlipayTradePayRequestBuilder setDiscountableAmount(String discountableAmount) {
        bizContent.discountableAmount = discountableAmount;
        return this;
    }

    public String getUndiscountableAmount() {
        return bizContent.undiscountableAmount;
    }

    public AlipayTradePayRequestBuilder setUndiscountableAmount(String undiscountableAmount) {
        bizContent.undiscountableAmount = undiscountableAmount;
        return this;
    }

    public String getSubject() {
        return bizContent.subject;
    }

    public AlipayTradePayRequestBuilder setSubject(String subject) {
        bizContent.subject = subject;
        return this;
    }

    public String getBody() {
        return bizContent.body;
    }

    public AlipayTradePayRequestBuilder setBody(String body) {
        bizContent.body = body;
        return this;
    }

    public List<GoodsDetail> getGoodsDetailList() {
        return bizContent.goodsDetailList;
    }

    public AlipayTradePayRequestBuilder setGoodsDetailList(List<GoodsDetail> goodsDetailList) {
        bizContent.goodsDetailList = goodsDetailList;
        return this;
    }

    public String getOperatorId() {
        return bizContent.operatorId;
    }

    public AlipayTradePayRequestBuilder setOperatorId(String operatorId) {
        bizContent.operatorId = operatorId;
        return this;
    }

    public String getStoreId() {
        return bizContent.storeId;
    }

    public AlipayTradePayRequestBuilder setStoreId(String storeId) {
        bizContent.storeId = storeId;
        return this;
    }

    public String getAlipayStoreId() {
        return bizContent.alipayStoreId;
    }

    public AlipayTradePayRequestBuilder setAlipayStoreId(String alipayStoreId) {
        bizContent.alipayStoreId = alipayStoreId;
        return this;
    }

    public String getTerminalId() {
        return bizContent.terminalId;
    }

    public AlipayTradePayRequestBuilder setTerminalId(String terminalId) {
        bizContent.terminalId = terminalId;
        return this;
    }

    public ExtendParams getExtendParams() {
        return bizContent.extendParams;
    }

    public AlipayTradePayRequestBuilder setExtendParams(ExtendParams extendParams) {
        bizContent.extendParams = extendParams;
        return this;
    }

    public String getTimeoutExpress() {
        return bizContent.timeoutExpress;
    }

    public AlipayTradePayRequestBuilder setTimeoutExpress(String timeoutExpress) {
        bizContent.timeoutExpress = timeoutExpress;
        return this;
    }

    public static class BizContent {
        private String scene;

       @SerializedName("auth_code")
        private String authCode;

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
            sb.append("scene='").append(scene).append('\'');
            sb.append(", authCode='").append(authCode).append('\'');
            sb.append(", outTradeNo='").append(outTradeNo).append('\'');
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
