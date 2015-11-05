package com.optigra.youpeople.dto;

import java.util.List;

/**
 * Created by romanmudryi on 01.09.15.
 */
public class IndustryGroupDTO {

    private Long id;

    private String name;

    private List<IndustryIndustryGroupDTO> industryIndustryGroups;

    public IndustryGroupDTO() {
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

    public List<IndustryIndustryGroupDTO> getIndustryIndustryGroups() {
        return industryIndustryGroups;
    }

    public void setIndustryIndustryGroups(List<IndustryIndustryGroupDTO> industryIndustryGroups) {
        this.industryIndustryGroups = industryIndustryGroups;
    }
}
