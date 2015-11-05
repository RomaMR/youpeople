package com.optigra.youpeople.domain.education;

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
@Table(name = "t_education")
@Audited
public class Education extends Experience implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "degree")
    private String degree;

    @Column(name = "field_of_study")
    private String fieldOfStudy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private LinkedinPerson linkedinPerson;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Education() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
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
        if (!(o instanceof Education)) return false;

        final Education education = (Education) o;

        if (id != null ? !id.equals(education.id) : education.id != null) return false;
        if (degree != null ? !degree.equals(education.degree) : education.degree != null) return false;
        if (fieldOfStudy != null ? !fieldOfStudy.equals(education.fieldOfStudy) : education.fieldOfStudy != null) return false;
        if (linkedinPerson != null ? !linkedinPerson.equals(education.linkedinPerson) : education.linkedinPerson != null) return false;
        return !(organization != null ? !organization.equals(education.organization) : education.organization != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (fieldOfStudy != null ? fieldOfStudy.hashCode() : 0);
        result = 31 * result + (linkedinPerson != null ? linkedinPerson.hashCode() : 0);
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        return result;
    }
}
