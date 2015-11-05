package com.optigra.youpeople.dto;

import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
public class OrganizationDTO {

    private Long id;
    private String name;
    private List<JobDTO> jobs;
    private List<VolunteeringDTO> volunteerings;
    private List<EducationDTO> educations;

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

    public List<JobDTO> getJobs() {
        return jobs;
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

    @Override
    public String toString() {
        return "OrganizationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}
