package com.optigra.youpeople.domain.industry.industrygroups;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.industry.group.IndustryGroup;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by romanmudryi on 01.09.15.
 */
@Entity
@Table(name = "t_industry_industry_group")
public class IndustryIndustryGroup extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "industry_group_id")
    private IndustryGroup industryGroup;

    @Column(name = "main")
    private Boolean main;

    public IndustryIndustryGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public IndustryGroup getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(IndustryGroup industryGroup) {
        this.industryGroup = industryGroup;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }
}
