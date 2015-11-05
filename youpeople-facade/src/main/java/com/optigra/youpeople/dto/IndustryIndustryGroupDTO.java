package com.optigra.youpeople.dto;

/**
 * Created by romanmudryi on 01.09.15.
 */
public class IndustryIndustryGroupDTO {

    private Long id;

    private IndustryDTO industry;

    private IndustryGroupDTO industryGroup;

    private Boolean main;

    public IndustryIndustryGroupDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IndustryDTO getIndustry() {
        return industry;
    }

    public void setIndustry(IndustryDTO industry) {
        this.industry = industry;
    }

    public IndustryGroupDTO getIndustryGroup() {
        return industryGroup;
    }

    public void setIndustryGroup(IndustryGroupDTO industryGroup) {
        this.industryGroup = industryGroup;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }
}
