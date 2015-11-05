package com.optigra.youpeople.dto;

import java.util.Date;

/**
 * Created by oleh on 06.08.15.
 */
public class JobDTO {

    private Long id;
    private String position;
    private Date startDate;
    private Date endDate;
    
    // FIXME Job DTO should NOT include Person DTO.
    private PersonDTO person;
    
    // FIXME Job DTO should NOT include Organization DTO.
    private OrganizationDTO organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", person=" + person +
                ", organization=" + organization +
                '}';
    }
}
