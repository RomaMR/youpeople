package com.optigra.youpeople.view.industry;

import javax.persistence.*;

/**
 * Created by romanmudryi on 03.09.15.
 */
@Entity
@Table(name = "industry_group_statistic")
public class IndustryGroupStatisticView {

    @Id
    private Long id;

    private String name;

    private Long count;

    public IndustryGroupStatisticView() {
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
