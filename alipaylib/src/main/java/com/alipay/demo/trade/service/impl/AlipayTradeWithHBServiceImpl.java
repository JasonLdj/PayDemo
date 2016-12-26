package com.alipay.demo.trade.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
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
import com.alipay.demo.trade.service.impl.hb.HbListener;
import com.alipay.demo.trade.service.impl.hb.TradeListener;
import org.apache.commons.lang.StringUtils;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketException;
import java.net.SocketTimeoutException;


/**
 * Created by liuyangkly on 15/7/29.
 *
 *
 */
public class AlipayTradeWithHBServiceImpl extends AbsAlipayTradeService {
    private TradeListener listener;

    public static class ClientBuilder {
        private String gatewayUrl;
        private String appid;
        private String privateKey;
        private String format;
        private String charset;
        private String alipayPublicKey;
        private TradeListener listener;

        public AlipayTradeWithHBServiceImpl build() {
            if (StringUtils.isEmpty(gatewayUrl)) {
                gatewayUrl = Configs.getOpenApiDomain(); //
            }
            if (StringUtils.isEmpty(appid)) {
                appid = Configs.getAppid();
            }
            if (StringUtils.isEmpty(privateKey)) {
                privateKey = Configs.getPrivateKey();
            }
            if (StringUtils.isEmpty(format)) {
                format = "json";
            }
            if (StringUtils.isEmpty(charset)) {
                charset = "utf-8";
            }
            if (StringUtils.isEmpty(alipayPublicKey)) {
                alipayPublicKey = Configs.getAlipayPublicKey();
            }
            if (listener == null) {
                listener = new HbListener();  //
            }

            return new AlipayTradeWithHBServiceImpl(this);
        }

        public TradeListener getListener() {
            return listener;
        }

        public ClientBuilder setListener(TradeListener listener) {
            this.listener = listener;
            return this;
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

    public AlipayTradeWithHBServiceImpl(ClientBuilder builder) {
        if (StringUtils.isEmpty(builder.getGatewayUrl())) {
            throw new NullPointerException("gatewayUrl should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getAppid())) {
            throw new NullPointerException("appid should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getPrivateKey())) {
            throw new NullPointerException("privateKey should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getFormat())) {
            throw new NullPointerException("format should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getCharset())) {
            throw new NullPointerException("charset should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getAlipayPublicKey())) {
            throw new NullPointerException("alipayPublicKey should not be NULL!");
        }
        if (builder.getListener() == null) {
            throw new NullPointerException("listener should not be NULL!");
        }

        listener = builder.getListener();
        client = new DefaultAlipayClient(builder.getGatewayUrl(), builder.getAppid(), builder.getPrivateKey(),
                builder.getFormat(), builder.getCharset(), builder.getAlipayPublicKey());
    }

    private AlipayTradePayResponse getResponse(AlipayClient client, AlipayTradePayRequest request,
                                                 final String outTradeNo, final long beforeCall) {
        try {
            AlipayTradePayResponse response = client.execute(request);
            if (response != null) {
                log.info(response.getBody());
            }
            return response;

        } catch (AlipayApiException e) {
            // 获取异常真实原因
            Throwable cause = e.getCause();

            if (cause instanceof ConnectException ||
                    cause instanceof NoRouteToHostException) {
                //
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        listener.onConnectException(outTradeNo, beforeCall);
                    }
                });

            } else if (cause instanceof SocketException) {
                //
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSendException(outTradeNo, beforeCall);
                    }
                });

            } else if (cause instanceof SocketTimeoutException) {
                //
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        listener.onReceiveException(outTradeNo, beforeCall);
                    }
                });
            }

            e.printStackTrace();
            return null;
        }
    }

    //
    @Override
    public AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder) {
        validateBuilder(builder);

        final String outTradeNo = builder.getOutTradeNo();

        AlipayTradePayRequest request = new AlipayTradePayRequest();
        //
        String appAuthToken = builder.getAppAuthToken();
        request.setNotifyUrl(builder.getNotifyUrl());
        request.putOtherTextParam("app_auth_token", appAuthToken);

        //
        request.setBizContent(builder.toJsonString());
        log.info("trade.pay bizContent:" + request.getBizContent());

        //
        final long beforeCall = System.currentTimeMillis();
        AlipayTradePayResponse response = getResponse(client, request, outTradeNo, beforeCall);

        AlipayF2FPayResult result = new AlipayF2FPayResult(response);
        if (response != null && Constants.SUCCESS.equals(response.getCode())) {
            //
            result.setTradeStatus(TradeStatus.SUCCESS);

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    listener.onPayTradeSuccess(outTradeNo, beforeCall);
                }
            });

        } else if (response != null && Constants.PAYING.equals(response.getCode())) {
            //
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    listener.onPayInProgress(outTradeNo, beforeCall);
                }
            });

            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                    .setAppAuthToken(appAuthToken)
                    .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse loopQueryResponse = loopQueryResult(queryBuiler);
            return checkQueryAndCancel(outTradeNo, appAuthToken, result, loopQueryResponse);

        } else if (tradeError(response)) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    listener.onPayFailed(outTradeNo, beforeCall);
                }
            });

            AlipayTradeQueryRequestBuilder queryBuiler = new AlipayTradeQueryRequestBuilder()
                    .setAppAuthToken(appAuthToken)
                    .setOutTradeNo(outTradeNo);
            AlipayTradeQueryResponse queryResponse = tradeQuery(queryBuiler);
            return checkQueryAndCancel(outTradeNo, appAuthToken, result, queryResponse);

        } else {

            result.setTradeStatus(TradeStatus.FAILED);

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    listener.onPayFailed(outTradeNo, beforeCall);
                }
            });
        }

        return result;
    }
}
