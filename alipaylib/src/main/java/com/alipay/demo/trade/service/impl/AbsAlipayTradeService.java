package com.alipay.demo.trade.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.*;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.config.Constants;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.builder.AlipayTradeCancelRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeRefundRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.utils.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by liuyangkly on 15/10/28.
 */
abstract class AbsAlipayTradeService extends AbsAlipayService implements AlipayTradeService {
    protected static ExecutorService executorService = Executors.newCachedThreadPool();
    protected AlipayClient client;

    @Override
    public AlipayF2FQueryResult queryTradeResult(AlipayTradeQueryRequestBuilder builder) {
        AlipayTradeQueryResponse response = tradeQuery(builder);

        AlipayF2FQueryResult result = new AlipayF2FQueryResult(response);
        if (querySuccess(response)) {

            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (tradeError(response)) {

            result.setTradeStatus(TradeStatus.UNKNOWN);

        } else {

            result.setTradeStatus(TradeStatus.FAILED);
        }
        return result;
    }

    protected AlipayTradeQueryResponse tradeQuery(AlipayTradeQueryRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.query bizContent:" + request.getBizContent());

        return (AlipayTradeQueryResponse) getResponse(client, request);
    }

    @Override
    public AlipayF2FRefundResult tradeRefund(AlipayTradeRefundRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setNotifyUrl(builder.getNotifyUrl());
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.refund bizContent:" + request.getBizContent());

        AlipayTradeRefundResponse response = (AlipayTradeRefundResponse) getResponse(client, request);

        AlipayF2FRefundResult result = new AlipayF2FRefundResult(response);
        if (response != null && Constants.SUCCESS.equals(response.getCode())) {

            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (tradeError(response)) {

            result.setTradeStatus(TradeStatus.UNKNOWN);

        } else {

            result.setTradeStatus(TradeStatus.FAILED);
        }
        return result;
    }

    @Override
    public AlipayF2FPrecreateResult tradePrecreate(AlipayTradePrecreateRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl(builder.getNotifyUrl());
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.precreate bizContent:" + request.getBizContent());

        AlipayTradePrecreateResponse response = (AlipayTradePrecreateResponse) getResponse(client, request);

        AlipayF2FPrecreateResult result = new AlipayF2FPrecreateResult(response);
        if (response != null && Constants.SUCCESS.equals(response.getCode())) {

            result.setTradeStatus(TradeStatus.SUCCESS);

        } else if (tradeError(response)) {

            result.setTradeStatus(TradeStatus.UNKNOWN);

        } else {

            result.setTradeStatus(TradeStatus.FAILED);
        }
        return result;
    }

    protected AlipayF2FPayResult checkQueryAndCancel(String outTradeNo, String appAuthToken, AlipayF2FPayResult result,
                                                   AlipayTradeQueryResponse queryResponse) {
        if (querySuccess(queryResponse)) {
            result.setTradeStatus(TradeStatus.SUCCESS);
            result.setResponse(toPayResponse(queryResponse));
            return result;
        }

        AlipayTradeCancelRequestBuilder builder = new AlipayTradeCancelRequestBuilder().setOutTradeNo(outTradeNo);
        builder.setAppAuthToken(appAuthToken);
        AlipayTradeCancelResponse cancelResponse = cancelPayResult(builder);
        if (tradeError(cancelResponse)) {
            result.setTradeStatus(TradeStatus.UNKNOWN);
        } else {
            result.setTradeStatus(TradeStatus.FAILED);
        }
        return result;
    }

    protected AlipayTradeCancelResponse tradeCancel(AlipayTradeCancelRequestBuilder builder) {
        validateBuilder(builder);

        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("trade.cancel bizContent:" + request.getBizContent());

        return (AlipayTradeCancelResponse) getResponse(client, request);
    }

    protected AlipayTradeQueryResponse loopQueryResult(AlipayTradeQueryRequestBuilder builder) {
        AlipayTradeQueryResponse queryResult = null;
        for (int i = 0; i < Configs.getMaxQueryRetry(); i++) {
            Utils.sleep(Configs.getQueryDuration());

            AlipayTradeQueryResponse response = tradeQuery(builder);
            if (response != null) {
                if (stopQuery(response)) {
                    return response;
                }
                queryResult = response;
            }
        }
        return queryResult;
    }

    protected boolean stopQuery(AlipayTradeQueryResponse response) {
        if (Constants.SUCCESS.equals(response.getCode())) {
            if ("TRADE_FINISHED".equals(response.getTradeStatus()) ||
                    "TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                    "TRADE_CLOSED".equals(response.getTradeStatus())) {
                return true;
            }
        }
        return false;
    }

    protected AlipayTradeCancelResponse cancelPayResult(AlipayTradeCancelRequestBuilder builder) {
        AlipayTradeCancelResponse response = tradeCancel(builder);
        if (cancelSuccess(response)) {
            return response;
        }

        if (needRetry(response)) {
            log.warn("begin async cancel request:" + builder);
            asyncCancel(builder);
        }
        return response;
    }

    protected void asyncCancel(final AlipayTradeCancelRequestBuilder builder) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Configs.getMaxCancelRetry(); i++) {
                    Utils.sleep(Configs.getCancelDuration());

                    AlipayTradeCancelResponse response = tradeCancel(builder);
                    if (cancelSuccess(response) ||
                            !needRetry(response)) {
                        return ;
                    }
                }
            }
        });
    }

    protected AlipayTradePayResponse toPayResponse(AlipayTradeQueryResponse response) {
        AlipayTradePayResponse payResponse = new AlipayTradePayResponse();
        payResponse.setCode(querySuccess(response) ? Constants.SUCCESS : Constants.FAILED);
        StringBuilder msg = new StringBuilder(response.getMsg())
                .append(" tradeStatus:")
                .append(response.getTradeStatus());
        payResponse.setMsg(msg.toString());
        payResponse.setSubCode(response.getSubCode());
        payResponse.setSubMsg(response.getSubMsg());
        payResponse.setBody(response.getBody());
        payResponse.setParams(response.getParams());

        // payResponse.setGmtPayment(response.getSendPayDate());
        payResponse.setBuyerLogonId(response.getBuyerLogonId());
        payResponse.setFundBillList(response.getFundBillList());
        payResponse.setOpenId(response.getOpenId());
        payResponse.setOutTradeNo(response.getOutTradeNo());
        payResponse.setReceiptAmount(response.getReceiptAmount());
        payResponse.setTotalAmount(response.getTotalAmount());
        payResponse.setTradeNo(response.getTradeNo());
        return payResponse;
    }

    protected boolean needRetry(AlipayTradeCancelResponse response) {
        return response == null ||
                "Y".equals(response.getRetryFlag());
    }

    protected boolean querySuccess(AlipayTradeQueryResponse response) {
        return response != null &&
                Constants.SUCCESS.equals(response.getCode()) &&
                ("TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                        "TRADE_FINISHED".equals(response.getTradeStatus())
                );
    }

    protected boolean cancelSuccess(AlipayTradeCancelResponse response) {
        return response != null &&
                Constants.SUCCESS.equals(response.getCode());
    }

    protected boolean tradeError(AlipayResponse response) {
        return response == null ||
                Constants.ERROR.equals(response.getCode());
    }
}
