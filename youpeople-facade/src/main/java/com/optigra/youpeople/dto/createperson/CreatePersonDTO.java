package com.optigra.youpeople.dto.createperson;

import java.util.List;

/**
 * Created by romanmudryi on 18.10.15.
 */
public class CreatePersonDTO {

    private String firstName;

    private String lastName;

    private String workEmail;

    private String personalEmail;

    private String locality;

    private String industry;

    private String facebookProfileId;

    private String linkedinProfileId;

    private Long organizationId;

    private List<CreatedPersonEducationDTO> educations;

    private List<CreatedPersonJobDTO> jobs;

    private List<CreatedPersonVolunteeringDTO> volunteerings;

    public CreatePersonDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(final String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(final String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(final String locality) {
        this.locality = locality;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(final String industry) {
        this.industry = industry;
    }

    public String getFacebookProfileId() {
        return facebookProfileId;
    }

    public void setFacebookProfileId(final String facebookProfileId) {
        this.facebookProfileId = facebookProfileId;
    }

    public String getLinkedinProfileId() {
        return linkedinProfileId;
    }

    public void setLinkedinProfileId(final String linkedinProfileId) {
        this.linkedinProfileId = linkedinProfileId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(final Long organizationId) {
        this.organizationId = organizationId;
    }

    public List<CreatedPersonEducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(final List<CreatedPersonEducationDTO> educations) {
        this.educations = educations;
    }

    public List<CreatedPersonJobDTO> getJobs() {
        return jobs;
    }

    public void setJobs(final List<CreatedPersonJobDTO> jobs) {
        this.jobs = jobs;
    }

    public List<CreatedPersonVolunteeringDTO> getVolunteerings() {
        return volunteerings;
    }

    public void setVolunteerings(final List<CreatedPersonVolunteeringDTO> volunteerings) {
        this.volunteerings = volunteerings;
    }
}
