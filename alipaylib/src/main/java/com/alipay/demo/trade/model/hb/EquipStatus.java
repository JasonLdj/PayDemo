package com.alipay.demo.trade.model.hb;

/**
 * Created by liuyangkly on 15/8/27.
 */
public enum EquipStatus {
    ON("10")

    ,OFF("20")

    ,NORMAL("30")

    ,SLEEP("40")

    ,AWAKE("41");

    private String value;

    EquipStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
