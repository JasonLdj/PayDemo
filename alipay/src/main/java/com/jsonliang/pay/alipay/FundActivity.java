package com.jsonliang.pay.alipay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsonliang.pay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FundActivity extends AppCompatActivity {
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.activity_fund)
    RelativeLayout activityFund;
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("退款成功");
        result = getIntent().getStringExtra("Fund");
        textView2.setText(result);
    }
}
