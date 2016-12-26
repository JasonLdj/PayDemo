package com.jsonliang.pay.baidupay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jsonliang.pay.R;
import com.jsonliang.pay.baidupay.beans.Params;

public class BaiduActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu);
        getSupportActionBar().setTitle("百度钱包-扫码支付");
        initPay();
    }

    private void initPay() {
       new Thread(){
           @Override
           public void run() {
               Params params = new Params();
               params.setCode_type("0");
               params.setCode_size("2");
               params.setOutput_type("json");
               params.setNologo("1");
               //params.set
           }
       }.start();
    }
}
