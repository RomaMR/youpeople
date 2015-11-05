package com.optigra.youpeople.domain.job;

import com.optigra.youpeople.domain.audit.AbstractEnversAuditing;
import com.optigra.youpeople.domain.audit.AuditingId;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by romanmudryi on 18.08.15.
 */
@IdClass(AuditingId.class)
@Table(name = "t_job_aud")
public class JobAuditing extends AbstractEnversAuditing implements Serializable {

    @Column(name = "position")
    private String position;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private LinkedinPerson linkedinPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public JobAuditing() {
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

    public LinkedinPerson getLinkedinPerson() {
        return linkedinPerson;
    }

    public void setLinkedinPerson(LinkedinPerson linkedinPerson) {
        this.linkedinPerson = linkedinPerson;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof JobAuditing)) return false;
        if (!super.equals(o)) return false;

        final JobAuditing that = (JobAuditing) o;

        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (linkedinPerson != null ? !linkedinPerson.equals(that.linkedinPerson) : that.linkedinPerson != null) return false;
        return !(organization != null ? !organization.equals(that.organization) : that.organization != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (linkedinPerson != null ? linkedinPerson.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }
}
