package com.optigra.youpeople.domain.industry;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;
import com.optigra.youpeople.domain.industry.industrygroups.IndustryIndustryGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by romanmudryi on 01.09.15.
 */
@Entity
@Table(name = "t_industry")
public class Industry extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "industry")
    private List<IndustryIndustryGroup> industryIndustryGroups;

    public Industry() {
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

    public List<IndustryIndustryGroup> getIndustryIndustryGroups() {
        return industryIndustryGroups;
    }

    public void setIndustryIndustryGroups(List<IndustryIndustryGroup> industryIndustryGroups) {
        this.industryIndustryGroups = industryIndustryGroups;
    }
}
