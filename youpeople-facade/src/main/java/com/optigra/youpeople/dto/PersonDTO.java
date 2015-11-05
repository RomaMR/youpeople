package com.optigra.youpeople.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String workEmail;
    private String personalEmail;
    private String linkedinProfileId;
    private String locality;
    private Integer numberOfConnections;
    private String profilePicture;
    private IndustryDTO industry;
    private List<JobDTO> jobs;
    private JobDTO currentJob;
    private Date queriedCompanyLeaveDate;
    private List<VolunteeringDTO> volunteerings;
    private List<EducationDTO> educations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getLinkedinProfileId() {
        return linkedinProfileId;
    }

    public void setLinkedinProfileId(String linkedinProfileId) {
        this.linkedinProfileId = linkedinProfileId;
    }

    public List<JobDTO> getJobs() {
        return jobs;
    }

    public JobDTO getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(JobDTO currentJob) {
		this.currentJob = currentJob;
	}

	public Date getQueriedCompanyLeaveDate() {
		return queriedCompanyLeaveDate;
	}

	public void setQueriedCompanyLeaveDate(Date queriedCompanyLeaveDate) {
		this.queriedCompanyLeaveDate = queriedCompanyLeaveDate;
	}

	public IndustryDTO getIndustry() {
        return industry;
    }

    public void setIndustry(IndustryDTO industry) {
        this.industry = industry;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Integer getNumberOfConnections() {
        return numberOfConnections;
    }

    public void setNumberOfConnections(Integer numberOfConnections) {
        this.numberOfConnections = numberOfConnections;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setJobs(List<JobDTO> jobs) {
        this.jobs = jobs;
    }

    public List<VolunteeringDTO> getVolunteerings() {
        return volunteerings;
    }

    public void setVolunteerings(List<VolunteeringDTO> volunteerings) {
        this.volunteerings = volunteerings;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

}
