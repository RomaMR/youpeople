package com.optigra.youpeople.enums;

/**
 * Created by romanmudryi on 04.09.15.
 */
public enum WorkStatus {
    ACTIVE("Active"), ALUMNI("Alumni"), YEAR_AGO("0-1"), TWO_AGO("1-2"), FOUR_AGO("2-4"), SIX_AGO("4-6"), TEN_AGO("6-10"),
    MORE_THAN_TEN_AGO(">10");

    private String value;

    WorkStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
