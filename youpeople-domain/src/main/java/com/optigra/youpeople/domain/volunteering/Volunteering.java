package com.optigra.youpeople.domain.volunteering;

import com.optigra.youpeople.domain.audit.Experience;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by romanmudryi on 14.08.15.
 */
@Entity
@Table(name = "t_volunteering")
@Audited
public class Volunteering extends Experience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cause")
    private String cause;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private LinkedinPerson linkedinPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Volunteering() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
        if (!(o instanceof Volunteering)) return false;

        final Volunteering that = (Volunteering) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (cause != null ? !cause.equals(that.cause) : that.cause != null) return false;
        if (linkedinPerson != null ? !linkedinPerson.equals(that.linkedinPerson) : that.linkedinPerson != null) return false;
        return !(organization != null ? !organization.equals(that.organization) : that.organization != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cause != null ? cause.hashCode() : 0);
        result = 31 * result + (linkedinPerson != null ? linkedinPerson.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }
}
