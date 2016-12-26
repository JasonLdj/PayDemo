package com.alipay.demo.trade.service.impl;

import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.config.Constants;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import org.apache.commons.lang.StringUtils;

/**
 * Created by liuyangkly on 15/7/29.
 *
 *
 */
public class AlipayTradeServiceImpl extends AbsAlipayTradeService {

    public static class ClientBuilder {
        private String gatewayUrl;
        private String appid;
        private String privateKey;
        private String format;
        private String charset;
        private String alipayPublicKey;

        public static boolean isEmpty(String str) {
            return str == null || str.length() == 0;
        }
        public AlipayTradeServiceImpl build() {
            if (isEmpty(gatewayUrl)) {
                gatewayUrl = Configs.getOpenApiDomain(); //
            }
            if (isEmpty(appid)) {
                appid = Configs.getAppid();
            }
            if (isEmpty(privateKey)) {
                privateKey = Configs.getPrivateKey();
            }
            if (isEmpty(format)) {
                format = "json";
            }
            if (isEmpty(charset)) {
                charset = "utf-8";
            }
            if (isEmpty(alipayPublicKey)) {
                alipayPublicKey = Configs.getAlipayPublicKey();
            }

            return new AlipayTradeServiceImpl(this);
        }

        public ClientBuilder setAlipayPublicKey(String alipayPublicKey) {
            this.alipayPublicKey = alipayPublicKey;
            return this;
        }

        public ClientBuilder setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public ClientBuilder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        public ClientBuilder setFormat(String format) {
            this.format = format;
            return this;
        }

        public ClientBuilder setGatewayUrl(String gatewayUrl) {
            this.gatewayUrl = gatewayUrl;
            return this;
        }

        public ClientBuilder setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public String getAlipayPublicKey() {
            return alipayPublicKey;
        }

        public String getAppid() {
            return appid;
        }

        public String getCharset() {
            return charset;
        }

        public String getFormat() {
            return format;
        }

        public String getGatewayUrl() {
            return gatewayUrl;
        }

        public String getPrivateKey() {
            return privateKey;
        }
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
    public AlipayTradeServiceImpl(ClientBuilder builder) {
        if (isEmpty(builder.getGatewayUrl())) {
            throw new NullPointerException("gatewayUrl should not be NULL!");
        }
        if (isEmpty(builder.getAppid())) {
            throw new NullPointerException("appid should not be NULL!");
        }
        if (isEmpty(builder.getPrivateKey())) {
            throw new NullPointerException("privateKey should not be NULL!");
        }
        if (isEmpty(builder.getFormat())) {
            throw new NullPointerException("format should not be NULL!");
        }
        if (isEmpty(builder.getCharset())) {
            throw new NullPointerException("charset should not be NULL!");
        }
        if (isEmpty(builder.getAlipayPublicKey())) {
            throw new NullPointerException("alipayPublicKey should not be NULL!");
        }

        client = new DefaultAlipayClient(builder.getGatewayUrl(), builder.getAppid(), builder.getPrivateKey(),
                builder.getFormat(), builder.getCharset(), builder.getAlipayPublicKey());
    }

    @Override
    public AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder) {
        validateBuilder(builder);

        final String outTradeNo = builder.getOutTradeNo();

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        request.setNotifyUrl(builder.getNotifyUrl());
        String appAuthToken = builder.getAppAuthToken();
        // request.setAppAuthToken(appAuthToken);
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());

        request.setBizContent(builder.toJsonString());
        log.info("trade.pay bizContent:" + request.getBizContent());

        AlipayTradePayResponse response = (AlipayTradePayResponse) getResponse(client, request);

        AlipayF2FPayResult result = new AlipayF2FPayResult(response);
        if (response != null && Constants.SUCCESS.equals(response.getCode())) {
            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (response != null && Constants.PAYING.equals(response.getCode())) {
            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                                                            .setAppAuthToken(appAuthToken)
                                                            .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse loopQueryResponse = loopQueryResult(queryBuiler);
            return checkQueryAndCancel(outTradeNo, appAuthToken, result, loopQueryResponse);

        } else if (tradeError(response)) {

            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                                                            .setAppAuthToken(appAuthToken)
                                                            .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse queryResponse = tradeQuery(queryBuiler);
            return checkQueryAndCancel(outTradeNo, appAuthToken, result, queryResponse);

        } else {

            result.setTradeStatus(TradeStatus.FAILED);
        }

        return result;
    }
}
