package com.optigra.youpeople.dto;

import java.math.BigDecimal;

/**
 * Created by romanmudryi on 07.09.15.
 */
public class EmployeeTurnoverStatisticDTO {

    private String year;

    private Long count;

    private Long fired;

    private Long hired;

    private BigDecimal turnover;

    private String workStatus;

    public EmployeeTurnoverStatisticDTO() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFired() {
        return fired;
    }

    public void setFired(Long fired) {
        this.fired = fired;
    }

    public Long getHired() {
        return hired;
    }

    public void setHired(Long hired) {
        this.hired = hired;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
