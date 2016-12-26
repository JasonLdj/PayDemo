package com.alipay.demo.trade.model.builder;

import com.alipay.demo.trade.utils.GsonFactory;

/**
 * Created by liuyangkly on 15/7/31.
 */
public abstract class RequestBuilder {
    private String appAuthToken;
    private String notifyUrl;

    // 验证请求对象
    public abstract boolean validate();


    public abstract Object getBizContent();

    //
    public String toJsonString() {
        //
        /**
         * See https://sites.google.com/site/gson/gson-user-guide#TOC-Using-Gson
         * Object Examples

         class BagOfPrimitives {
         private int value1 = 1;
         private String value2 = "abc";
         private transient int value3 = 3;
         BagOfPrimitives() {
         // no-args constructor
         }
         }

         (Serialization)
         BagOfPrimitives obj = new BagOfPrimitives();
         Gson gson = new Gson();
         String json = gson.toJson(obj);
         ==> json is {"value1":1,"value2":"abc"}
         */
        return GsonFactory.getGson().toJson(this.getBizContent());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestBuilder{");
        sb.append("appAuthToken='").append(appAuthToken).append('\'');
        sb.append(", notifyUrl='").append(notifyUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public RequestBuilder setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
        return this;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public RequestBuilder setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
        return this;
    }
}
