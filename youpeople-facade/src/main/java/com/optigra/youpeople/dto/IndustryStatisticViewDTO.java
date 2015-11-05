package com.optigra.youpeople.dto;

/**
 * Created by romanmudryi on 03.09.15.
 */
public class IndustryStatisticViewDTO {

    private Long id;

    private String name;

    private Long count;

    public IndustryStatisticViewDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
