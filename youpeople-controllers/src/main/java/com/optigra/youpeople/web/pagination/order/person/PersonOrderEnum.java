package com.optigra.youpeople.web.pagination.order.person;

import com.optigra.youpeople.web.pagination.order.OrderEnum;

/**
 * Created by romanmudryi on 20.08.15.
 */
public enum PersonOrderEnum implements OrderEnum {

    LAST_NAME("lastName"), INDUSTRY("industry"), LOCALITY("locality");

    private String value;

    PersonOrderEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
