package com.jsonliang.pay.alipay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.google.gson.Gson;
import com.jsonliang.pay.Constant;
import com.jsonliang.pay.R;
import com.jsonliang.pay.alipay.bean.FundBean;
import com.jsonliang.pay.alipay.bean.Query_Bean;
import com.jsonliang.pay.alipay.utils.MyDefaultClients;

public class Pay_Result extends AppCompatActivity {
    private static final String TAG = Pay_Result.class.getSimpleName();
    private String body = "" ;
    private ProgressDialog mWait ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay__result);
        getSupportActionBar().setTitle("交易成功");
         body = getIntent().getStringExtra("body");
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(body+"");
    }

    public void btn_fund(View view) {
        mWait = new ProgressDialog(Pay_Result.this);
        mWait.setTitle("退款请求发送中...");
        mWait.setCanceledOnTouchOutside(false);
        mWait.show();
        new Thread(){
            @Override
            public void run() {
                Query_Bean res = new Gson().fromJson(body,Query_Bean.class);
                AlipayClient alipayClient = new MyDefaultClients(
                        Constant.SERVER_URL,
                        Constant.APP_ID,
                        Constant.privateKey,
                        "json",
                        "UTF-8",
                         Constant.alipayPublicKey);
                AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
                Log.i(TAG, "run: " +res.getAlipay_trade_query_response().getOut_trade_no()+
                "," + res.getAlipay_trade_query_response().getTrade_no()+
                ","+res.getAlipay_trade_query_response().getBuyer_pay_amount());
                FundBean fund = new FundBean();
                fund.setOut_trade_no(res.getAlipay_trade_query_response().getOut_trade_no());
                fund.setTrade_no(res.getAlipay_trade_query_response().getTrade_no());
                fund.setRefund_amount(res.getAlipay_trade_query_response().getBuyer_pay_amount());
                fund.setStore_id(Constant.Store_id);
                fund.setOperator_id(Constant.Operator_id);
                fund.setTerminal_id(Constant.Terminal_id);
                fund.setRefund_reason("正常退款");
                fund.setOut_request_no("HZ01RF"+System.currentTimeMillis());//请求订单号
                request.setBizContent(new Gson().toJson(fund));
                AlipayResponse response = null;
                try {
                    response = alipayClient.execute(request);
                    if(mWait !=null){
                        mWait.dismiss();
                    }
                    if(response.isSuccess()){
                        Intent intent = new Intent(Pay_Result.this,FundActivity.class);
                        intent.putExtra("Fund",response.getBody());
                        startActivity(intent);
                        Pay_Result.this.finish();
                    } else {
                        Log.i(TAG, "run: " + response.getBody());
                    }
                } catch (AlipayApiException e) {
                    if(mWait !=null){
                        mWait.dismiss();
                    }
                }
            }
        }.start();
    }
}
