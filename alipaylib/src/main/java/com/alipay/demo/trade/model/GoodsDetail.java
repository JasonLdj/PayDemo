package com.alipay.demo.trade.model;


import com.alipay.demo.trade.utils.Utils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by liuyangkly on 15/6/26.
 */
public class GoodsDetail {
    @SerializedName("goods_id")
    private String goodsId;

    @SerializedName("alipay_goods_id")
    private String alipayGoodsId;

    @SerializedName("goods_name")
    private String goodsName;
    private int quantity;

    private String price;

    @SerializedName("goods_category")
    private String goodsCategory;

    private String body;

    public static GoodsDetail newInstance(String goodsId, String goodsName, long price, int quantity) {
        GoodsDetail info = new GoodsDetail();
        info.setGoodsId(goodsId);
        info.setGoodsName(goodsName);
        info.setPrice(price);
        info.setQuantity(quantity);
        return info;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GoodsDetail{");
        sb.append("goodsId='").append(goodsId).append('\'');
        sb.append(", alipayGoodsId='").append(alipayGoodsId).append('\'');
        sb.append(", goodsName='").append(goodsName).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", price='").append(price).append('\'');
        sb.append(", goodsCategory='").append(goodsCategory).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public GoodsDetail setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getAlipayGoodsId() {
        return alipayGoodsId;
    }

    public GoodsDetail setAlipayGoodsId(String alipayGoodsId) {
        this.alipayGoodsId = alipayGoodsId;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public GoodsDetail setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public GoodsDetail setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public GoodsDetail setPrice(long price) {
        this.price = Utils.toAmount(price);
        return this;
    }

    public String getGoodsCategory() {
        return goodsCategory;
    }

    public GoodsDetail setGoodsCategory(String goodsCategory) {
        this.goodsCategory = goodsCategory;
        return this;
    }

    public String getBody() {
        return body;
    }

    public GoodsDetail setBody(String body) {
        this.body = body;
        return this;
    }
}
