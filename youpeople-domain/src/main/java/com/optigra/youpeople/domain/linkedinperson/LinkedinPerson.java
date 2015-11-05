package com.optigra.youpeople.domain.linkedinperson;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;
import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by romanmudryi on 05.08.15.
 */
@Entity
@Table(name = "t_linkedin_person")
@Audited
public class LinkedinPerson extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "work_email")
    private String workEmail;

    @Column(name = "personal_email")
    private String personalEmail;
    
    @Column(name = "linkedin_profile_id")
    private String linkedinProfileId;

    @Column(name = "locality")
    private String locality;

    @Column(name = "number_of_connections")
    private Integer numberOfConnections;

    @Column(name = "profile_picture")
    private String profilePicture;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    @NotAudited//TODO auditing of industry
    private Industry industry;

    @NotAudited
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "linkedinPerson")
    @OrderBy("start_date ASC")
    private List<Job> jobs;

    @NotAudited
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "linkedinPerson")
    private List<Volunteering> volunteerings;

    @NotAudited
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "linkedinPerson")
    private List<Education> educations;

    public LinkedinPerson() {
    }

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

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
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

	public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Volunteering> getVolunteerings() {
        return volunteerings;
    }

    public void setVolunteerings(List<Volunteering> volunteerings) {
        this.volunteerings = volunteerings;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedinPerson)) return false;

        LinkedinPerson person = (LinkedinPerson) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        if (workEmail != null ? !workEmail.equals(person.workEmail) : person.workEmail != null) return false;
        if (personalEmail != null ? !personalEmail.equals(person.personalEmail) : person.personalEmail != null) return false;
        if (linkedinProfileId != null ? !linkedinProfileId.equals(person.linkedinProfileId) : person.linkedinProfileId != null) return false;
        if (industry != null ? !industry.equals(person.industry) : person.industry != null) return false;
        if (locality != null ? !locality.equals(person.locality) : person.locality != null) return false;
        if (numberOfConnections != null ? !numberOfConnections.equals(person.numberOfConnections) : person.numberOfConnections != null) return false;
        if (profilePicture != null ? !profilePicture.equals(person.profilePicture) : person.profilePicture != null) return false;
        if (jobs != null ? !jobs.equals(person.jobs) : person.jobs != null) return false;
        if (volunteerings != null ? !volunteerings.equals(person.volunteerings) : person.volunteerings != null) return false;
        return !(educations != null ? !educations.equals(person.educations) : person.educations != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (workEmail != null ? workEmail.hashCode() : 0);
        result = 31 * result + (personalEmail != null ? personalEmail.hashCode() : 0);
        result = 31 * result + (linkedinProfileId != null ? linkedinProfileId.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (numberOfConnections != null ? numberOfConnections.hashCode() : 0);
        result = 31 * result + (profilePicture != null ? profilePicture.hashCode() : 0);
        result = 31 * result + (jobs != null ? jobs.hashCode() : 0);
        result = 31 * result + (volunteerings != null ? volunteerings.hashCode() : 0);
        result = 31 * result + (educations != null ? educations.hashCode() : 0);
        return result;
    }
}
