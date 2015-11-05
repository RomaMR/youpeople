package com.optigra.youpeople.dto;

import java.util.List;

/**
 * Created by romanmudryi on 20.08.15.
 */
public class OrganizationPersonsPropertiesDTO {

    private Long organizationId;

    private String organizationName;

    private List<String> localities;

    private List<IndustryDTO> industries;

    public OrganizationPersonsPropertiesDTO() {
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<String> getLocalities() {
        return localities;
    }

    public void setLocalities(List<String> localities) {
        this.localities = localities;
    }

    public List<IndustryDTO> getIndustries() {
        return industries;
    }

    public void setIndustries(List<IndustryDTO> industries) {
        this.industries = industries;
    }
}
