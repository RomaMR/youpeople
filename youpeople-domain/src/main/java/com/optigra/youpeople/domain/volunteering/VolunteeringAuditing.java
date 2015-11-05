package com.optigra.youpeople.domain.volunteering;

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
@Table(name = "t_volunteering_aud")
public class VolunteeringAuditing extends AbstractEnversAuditing implements Serializable {

    @Column(name = "cause")
    private String cause;

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

    public VolunteeringAuditing() {
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
        if (!(o instanceof VolunteeringAuditing)) return false;
        if (!super.equals(o)) return false;

        final VolunteeringAuditing that = (VolunteeringAuditing) o;

        if (cause != null ? !cause.equals(that.cause) : that.cause != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (linkedinPerson != null ? !linkedinPerson.equals(that.linkedinPerson) : that.linkedinPerson != null) return false;
        return !(organization != null ? !organization.equals(that.organization) : that.organization != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (linkedinPerson != null ? linkedinPerson.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }
}
