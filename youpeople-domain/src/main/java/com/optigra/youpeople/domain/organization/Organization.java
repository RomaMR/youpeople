package com.optigra.youpeople.domain.organization;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;
import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.user.organization.UserOrganization;
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
@Table(name = "t_organization")
@Audited
public class Organization extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @NotAudited
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<Job> jobs;

    @NotAudited
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<Volunteering> volunteerings;

    @NotAudited
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<Education> educations;

    @NotAudited
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private List<UserOrganization> userOrganizations;

    public Organization() {
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
        if (!(o instanceof Organization)) return false;

        Organization that = (Organization) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (jobs != null ? !jobs.equals(that.jobs) : that.jobs != null) return false;
        if (volunteerings != null ? !volunteerings.equals(that.volunteerings) : that.volunteerings != null) return false;
        return !(educations != null ? !educations.equals(that.educations) : that.educations != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (jobs != null ? jobs.hashCode() : 0);
        result = 31 * result + (volunteerings != null ? volunteerings.hashCode() : 0);
        result = 31 * result + (educations != null ? educations.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		return "Organization [id()=" + getId() + ", name()=" + getName() + "]";
	}
    
}
