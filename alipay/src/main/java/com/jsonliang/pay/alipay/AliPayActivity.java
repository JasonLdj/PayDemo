package com.jsonliang.pay.alipay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.google.gson.Gson;
import com.jsonliang.pay.Constant;
import com.jsonliang.pay.R;
import com.jsonliang.pay.alipay.bean.OrderInfo;
import com.jsonliang.pay.alipay.bean.Query_Bean;
import com.jsonliang.pay.alipay.bean.RSP_Bean;
import com.jsonliang.pay.alipay.utils.MyDefaultClients;
import com.jsonliang.pay.alipay.utils.ZXingUtils;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AliPayActivity extends AppCompatActivity {
    private ProgressDialog mWait  ;
    private static final String TAG = AliPayActivity.class.getSimpleName();
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private String tradeNo = "" ;
    private String out_tradeNo = "" ; // 交易单号
    private String qr_code = "" ;// 交易二维码链接
    private Timer timer ;
    private String name ;
    private String price ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        getSupportActionBar().setTitle("支付宝-扫码支付");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = getIntent().getStringExtra("Name");
        price = getIntent().getStringExtra("Price");
        Log.i(TAG, "onCreate: " + name +","+price);
        ButterKnife.bind(this);
        mWait = new ProgressDialog(this);
        mWait.setTitle("二维码生成中...");
        mWait.show();
        getQrCode();
    }

    public void getQrCode() {
        new Thread() {
            @Override
            public void run() {
                // 1. 构建订单信息
                OrderInfo order = new OrderInfo();
                tradeNo = Calendar.getInstance().get(Calendar.YEAR) + System.currentTimeMillis()+"00001";
                order.setOut_trade_no(tradeNo);
                order.setTotal_amount(price);
                order.setSubject(name);
                order.setSeller_id(Constant.seller_id);// 商户ID
                order.setStore_id(Constant.Store_id);
                order.setTerminal_id(Constant.Terminal_id);
                order.setOperator_id(Constant.Operator_id);

                AlipayClient client = new MyDefaultClients(
                        "https://openapi.alipaydev.com/gateway.do",
                        Constant.APP_ID,
                        Constant.privateKey,
                        "json",
                        "UTF-8",
                        Constant.alipayPublicKey);
                AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
                request.setBizContent(new Gson().toJson(order));

                try {
                    AlipayResponse rs =  client.execute(request);
                    RSP_Bean response = (RSP_Bean) rs;
                    if(mWait !=null ){
                        mWait.dismiss();
                    }
                    if (response.isSuccess()) {
                        qr_code = response.getAlipay_trade_precreate_response().getQr_code();
                        out_tradeNo = response.getAlipay_trade_precreate_response().getOut_trade_no() ;
                        final Bitmap qrcode = ZXingUtils.createQRImage(qr_code,
                                300,300,BitmapFactory.decodeResource(getResources(),R.drawable.logo),null);
                        if(qrcode !=null){
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(qrcode);
                                    // 开一个线程，轮询是否订单被交易
                                    timer= new Timer();
                                    timer.scheduleAtFixedRate(new TimerTask() {
                                        @Override
                                        public void run() {
                                            query();
                                        }
                                    },1000,2000);// 每隔2秒查询一次
                                }
                            });
                        }
                    }
                } catch (AlipayApiException e) {
                    if(mWait !=null ){
                        mWait.dismiss();
                    }
                }
            }
        }.start();

    }

    /**
     * 查询订单交易情况
     */
    private void query(){
        AlipayClient alipayClient = new MyDefaultClients(
                Constant.SERVER_URL,
                 Constant.APP_ID,
                 Constant.privateKey,
                "json",
                "UTF-8",
                Constant.alipayPublicKey);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        Log.i(TAG, "query: " + tradeNo + " , outTrade:"+out_tradeNo);
        request.setBizContent("{" +
                "\"out_trade_no\":"+out_tradeNo +
                ",\"trade_no\":"+null+
                "}");
        try {
            AlipayResponse response =  alipayClient.execute(request);
            RSP_Bean rs = (RSP_Bean) response;
            if(rs.isSuccess()){
                Query_Bean res = new Gson().fromJson(rs.getBody(),Query_Bean.class);
                if("TRADE_SUCCESS".equals(res.getAlipay_trade_query_response().getTrade_status())){
                    timer.cancel();
                    Intent intent = new Intent(AliPayActivity.this, Pay_Result.class);
                    intent.putExtra("body",rs.getBody());
                    startActivity(intent);
                    AliPayActivity.this.finish();
                }

            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
