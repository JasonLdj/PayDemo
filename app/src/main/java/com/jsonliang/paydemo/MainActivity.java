package com.jsonliang.paydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jsonliang.paydemo.zhifubao_pay.ZFBaoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button[] btn = new Button[4];
    private EditText[] et = new EditText[2];
    private Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        et[0] = (EditText) findViewById(R.id.name);
        et[1] = (EditText) findViewById(R.id.price);

        btn[0] = (Button) findViewById(R.id.btn_wx_pay);
        btn[1] = (Button) findViewById(R.id.btn_zf_pay);
        btn[2] = (Button) findViewById(R.id.btn_baidu_pay);
        btn[3] = (Button) findViewById(R.id.btn_jd_pay);
        for (int i = 0; i < 4; i++) {
            btn[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wx_pay :
                break;
            case R.id.btn_zf_pay :
                intent = new Intent(MainActivity.this, ZFBaoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_baidu_pay :
                break;
            case R.id.btn_jd_pay :
                break;
        }
    }
}
