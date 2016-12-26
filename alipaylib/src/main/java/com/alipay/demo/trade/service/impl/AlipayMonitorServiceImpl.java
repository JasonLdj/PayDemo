package com.alipay.demo.trade.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.MonitorHeartbeatSynRequest;
import com.alipay.api.response.MonitorHeartbeatSynResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.builder.AlipayHeartbeatSynRequestBuilder;
import com.alipay.demo.trade.service.AlipayMonitorService;
import org.apache.commons.lang.StringUtils;

/**
 * Created by liuyangkly on 15/10/22.
 */
public class AlipayMonitorServiceImpl extends AbsAlipayService implements AlipayMonitorService {
    private AlipayClient client;

    public static class ClientBuilder {
        private String gatewayUrl;
        private String appid;
        private String privateKey;
        private String format;
        private String charset;

        public AlipayMonitorServiceImpl build() {
            if (StringUtils.isEmpty(gatewayUrl)) {
                gatewayUrl = Configs.getMcloudApiDomain(); // 与openapi网关地址不同
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
            return new AlipayMonitorServiceImpl(this);
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

    public AlipayMonitorServiceImpl(ClientBuilder builder) {
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


        client = new DefaultAlipayClient(builder.getGatewayUrl(), builder.getAppid(), builder.getPrivateKey(),
                builder.getFormat(), builder.getCharset());
    }

    @Override
    public MonitorHeartbeatSynResponse heartbeatSyn(AlipayHeartbeatSynRequestBuilder builder) {
        validateBuilder(builder);

        MonitorHeartbeatSynRequest request = new MonitorHeartbeatSynRequest();
        request.putOtherTextParam("app_auth_token", builder.getAppAuthToken());
        request.setBizContent(builder.toJsonString());
        log.info("heartbeat.sync bizContent:" + request.getBizContent());

        return (MonitorHeartbeatSynResponse) getResponse(client, request);
    }
}
