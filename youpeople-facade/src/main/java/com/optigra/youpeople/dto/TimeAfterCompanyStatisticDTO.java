package com.optigra.youpeople.dto;

/**
 * Created by romanmudryi on 04.09.15.
 */
public class TimeAfterCompanyStatisticDTO {

    private String name;

    private Long count;

    private String workStatus;

    public TimeAfterCompanyStatisticDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
