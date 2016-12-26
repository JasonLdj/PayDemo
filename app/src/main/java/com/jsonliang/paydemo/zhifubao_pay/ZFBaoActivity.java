package com.jsonliang.paydemo.zhifubao_pay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jsonliang.paydemo.R;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ZFBaoActivity extends AppCompatActivity {

    private static final String TAG = ZFBaoActivity.class.getSimpleName();
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.activity_zfbao)
    RelativeLayout activityZfbao;
    @BindView(R.id.response)
    TextView rs_text;

    Unbinder unbinder;
  //  private AlipayTradeService tradeService;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zfbao);
        unbinder = ButterKnife.bind(this);
        getSupportActionBar().setTitle("支付宝扫码支付");
      //  initConfig();
        //initAliPay();

        initPay();

    }

    private void initPay() {
        // 构建订单详情,并签名

    }

    private void initConfig() {
       // Configs.init();
       // tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

    }

    private void initAliPay() {
/*        new Thread() {
            @Override
            public void run() {
                String outTradeNo = "tradeprecreate" + System.currentTimeMillis() + (long)(Math.random() * 10000000L);
                String subject = "洗发水";
                String totalAmount = "1.0";
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
                        .setUndiscountableAmount(undiscountableAmount)
                        .setSellerId(sellerId)
                        .setBody(body)
                        .setOperatorId(operatorId)
                        .setStoreId(storeId)
                        .setExtendParams(extendParams)
                        .setTimeoutExpress(timeoutExpress)
                        .setGoodsDetailList(goodsDetailList);

                AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);


                AlipayTradePrecreateResponse response = result.getResponse();
                Log.i(TAG, "run: " + response);
                dumpResponse(response);
                switch (result.getTradeStatus()) {
                   // String filePath = String.format("/qr-%s.png", response.getOutTradeNo());
                    case SUCCESS:
                        Log.i(TAG, "run: 成功");
                        // ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                        break;

                    case FAILED:
                        Log.i(TAG, "run: FAILED");
                        break;

                    case UNKNOWN:
                      //  Log.i(TAG, "run: UNKNOWN" + result.getResponse().getMsg());
                        break;

                    default:
                        Log.i(TAG, "run:  default ");
                        break;
                }
            }
        }.start();
    }
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            Log.i(TAG, String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                Log.i(TAG, String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
            }
            Log.i(TAG, "body:" + response.getBody());
        }*/
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }
}
