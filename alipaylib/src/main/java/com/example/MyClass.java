package com.example;


import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;


public class MyClass {
    private static Log log = LogFactory.getLog(MyClass.class);

    private static AlipayTradeService tradeService;

    public MyClass(){
        Configs.init();
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    public void test_trade_precreate() {
        String outTradeNo = "tradeprecreate" + System.currentTimeMillis() + (long)(Math.random() * 10000000L);
        String subject = "xxxx";
        String totalAmount = "1";
        String undiscountableAmount = "0";
        String sellerId = "";
        String body = "20";
        String operatorId = "test_operator_id";
        String storeId = "test_store_id";
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");
        String timeoutExpress = "120m";

        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        GoodsDetail goods1 = GoodsDetail.newInstance("goods_id001", "xxxbread", 1000, 1);
        goodsDetailList.add(goods1);

        GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx yashua", 500, 2);
        goodsDetailList.add(goods2);

        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject)
                .setTotalAmount(totalAmount)
                .setOutTradeNo(outTradeNo)
            //   .setUndiscountableAmount(undiscountableAmount)
             //   .setSellerId(sellerId)
            //    .setBody(body)
             //   .setOperatorId(operatorId)
                .setStoreId(storeId);
             //   .setExtendParams(extendParams)
             //   .setTimeoutExpress(timeoutExpress)
             //   .setGoodsDetailList(goodsDetailList);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("xx: )");
                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);
                String filePath = String.format("/qr-%s.png", response.getOutTradeNo());
                log.info("filePath:" + filePath);
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                break;

            case FAILED:
                log.error("err!!!");
                break;

            case UNKNOWN:
                log.error("unknown!!!");
                break;

            default:
                log.error("err!!!");
                break;
        }
    }

    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

    public static void main(String[] args){
       new MyClass().test_trade_precreate();

/*        AlipayClient client = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016101900725033",
                "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANpcPPs+QX5sEtNzvNvIokImGS4u5YFd2y2B9KIZMnsn7PvvDxA/CQS+zcUrU3N5a5Q4HihlwDZ0TOHEqBd/IcjVol82mPlwoKVcdEslibVY42/10LRcuqNKDmZUiy6P/WEWI/+0NGktNQ/dF+0fwfH9iNIYm9eyIniaTdKTMPMlAgMBAAECgYBn96sBZS8B5qtACsdNTkYTgNhUaPfVlL6a+FlmJOEoSmZHvhdKAPlmTQLkcjCVtqZGOEIDsg7G3rEMffoa1elbOU4gJHTARy1raof+wI8n2aFH3sovqyb055D2j9fclItHIriD2tInnW+l1xmsF0zyj6iqyV71SWovcLRblGHDAQJBAPCfumXjFg48v2kYkQTPKqaZXgmDou65i0BDq5v/rJ3yPu/8X41V0moH93lv1z3YiZjdet+3GbrkHkYa+D4fWPUCQQDoUE7hibYhmM9GY22pS2O8rs3DVFVIaHb86Z/4DrQmrpgTDv4DDOfScRi6S65v/3HwFEzNgRcGtFIJFXJ2bpNxAkEA5CqR1Ys/nsB7wGxLFwRarR9AzETGexf1szp3v7TxOdQ7WdU1xg9bNq/XOo+SylJKJ2nc2cYF3/HvwwpP2phfAQJBALyp05W4R9A7EEHip4EwN3xZHcN+ro/SPqegd4h1MHqlmd0IdOM3TOELdgB9S9mqwvYtFj5ikregI2s7HqztmhECQQDUV0ui6ts6rt9RdDnSkB/p/3/faFEMUdPfU7SZmoeVj0NW2a6queXRvfS8ShL6DJBCutLrR5/9D8Hum8ER9Kd1"
        );

        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"20160320010101001\"," +
                "\"seller_id\":\"2088102180315995\"," +
                "\"total_amount\":88.88," +
               "\"discountable_amount\":0," +
                "\"undiscountable_amount\":88.88," +
                "\"buyer_logon_id\":\"15901825620\"," +
                "\"subject\":\"Iphone6 16G\"," +
                "\"body\":\"Iphone6 16G\"," +
                "\"goods_detail\":[{" +
                "\"goods_id\":\"apple-01\"," +
                "\"alipay_goods_id\":\"20010001\"," +
                "\"goods_name\":\"ipad\"," +
                "\"quantity\":1," +
                "\"price\":2000," +
                "\"goods_category\":\"34543238\"," +
                "\"body\":\"特价手机\"," +
                "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
                "}]," +
                "\"operator_id\":\"yx_001\"," +
                "\"store_id\":\"NJ_001\"," +
                "\"terminal_id\":\"NJ_T_001\"," +
                "\"extend_params\":{" +
                "\"sys_service_provider_id\":\"2088511833207846\"," +
                "\"hb_fq_num\":\"3\"," +
                "\"hb_fq_seller_percent\":\"100\"" +
                "}," +
                "\"timeout_express\":\"90m\"," +
                "\"royalty_info\":{" +
                "\"royalty_type\":\"ROYALTY\"," +
                "\"royalty_detail_infos\":[{" +
                "\"serial_no\":1," +
                "\"trans_in_type\":\"userId\"," +
                "\"batch_no\":\"123\"," +
                "\"out_relation_id\":\"20131124001\"," +
                "\"trans_out_type\":\"userId\"," +
                "\"trans_out\":\"2088101126765726\"," +
                "\"trans_in\":\"2088101126708402\"," +
                "\"amount\":0.1," +
                "\"desc\":\"分账测试1\"," +
                "\"amount_percentage\":\"100\"" +
                "}]" +
                "}," +
                "\"sub_merchant\":{" +
                "\"merchant_id\":\"19023454\"" +
                "}}");
        try {
            AlipayResponse response = client.execute(request);
            if(response.isSuccess()){
                System.out.println(response.getBody());
            }else{
                System.out.println(response.getBody()+","+response.getMsg()
                +response.getCode()+", "+response.getCode());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }*/
    }

   private void a(){
       try {
           BeanInfo e = Introspector.getBeanInfo(Integer.class.getClass());
           PropertyDescriptor[] pds = e.getPropertyDescriptors();
           boolean isResponseClazz = AlipayResponse.class.isAssignableFrom(Integer.class.getClass());
           PropertyDescriptor[] arr$ = pds;
       }catch (Exception e){

       }

    }
}
