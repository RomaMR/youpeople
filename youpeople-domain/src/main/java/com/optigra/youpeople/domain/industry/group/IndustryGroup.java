package com.optigra.youpeople.domain.industry.group;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by romanmudryi on 01.09.15.
 */
@Entity
@Table(name = "t_industry_group")
public class IndustryGroup extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public IndustryGroup() {
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
}
