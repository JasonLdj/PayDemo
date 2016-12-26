package com.jsonliang.pay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jsonliang.pay.alipay.AliPayActivity;
import com.jsonliang.pay.alipay.utils.EditInputFilter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.btn_wx_pay)
    Button btnWxPay;
    @BindView(R.id.btn_zf_pay)
    Button btnZfPay;
    @BindView(R.id.btn_baidu_pay)
    Button btnBaiduPay;
    @BindView(R.id.btn_jd_pay)
    Button btnJdPay;
    @BindView(R.id.activity_main2)
    LinearLayout activityMain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("输入商品信息");
        ButterKnife.bind(this);
        InputFilter[] inputFilter = {new EditInputFilter()} ;
        price.setFilters(inputFilter);
    }

    @OnClick({R.id.btn_wx_pay, R.id.btn_zf_pay, R.id.btn_baidu_pay, R.id.btn_jd_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_wx_pay:
                break;
            case R.id.btn_zf_pay:
                if(TextUtils.isEmpty(name.getText())){
                    Toast.makeText(MainActivity.this, "请输入商品名称", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(price.getText())){
                    Toast.makeText(MainActivity.this, "请输入商品价格", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, AliPayActivity.class);
                    intent.putExtra("Name",name.getText()+"");
                    intent.putExtra("Price",price.getText()+"");
                    startActivity(intent);
                }
                break;
            case R.id.btn_baidu_pay:
                break;
            case R.id.btn_jd_pay:
                break;
        }
    }
}
