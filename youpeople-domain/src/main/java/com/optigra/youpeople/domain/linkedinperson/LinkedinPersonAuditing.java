package com.optigra.youpeople.domain.linkedinperson;

import com.optigra.youpeople.domain.audit.AbstractEnversAuditing;
import com.optigra.youpeople.domain.audit.AuditingId;

import javax.persistence.Column;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by romanmudryi on 16.08.15.
 */
@IdClass(AuditingId.class)
@Table(name = "t_linkedin_person_aud")
public class LinkedinPersonAuditing extends AbstractEnversAuditing implements Serializable {

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

    public LinkedinPersonAuditing() {
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
}
