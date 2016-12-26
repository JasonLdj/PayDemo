package com.alipay.demo.trade.service.impl.hb;

/**
 * Created by liuyangkly on 15/10/27.
 */
public interface TradeListener {

    public void onPayTradeSuccess(String outTradeNo, long beforeCall);

    public void onPayInProgress(String outTradeNo, long beforeCall);

    public void onPayFailed(String outTradeNo, long beforeCall);


    public void onConnectException(String outTradeNo, long beforeCall);


    public void onSendException(String outTradeNo, long beforeCall);


    public void onReceiveException(String outTradeNo, long beforeCall);
}
