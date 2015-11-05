package com.optigra.youpeople.domain.person;

import com.optigra.youpeople.domain.audit.AbstractAuditingEntity;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by romanmudryi on 02.10.15.
 */
@Entity
@Table(name = "t_person")
public class Person extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uploaded_person_id")
    private UploadedPerson uploadedPerson;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "linkedin_person_id")
    private LinkedinPerson linkedinPerson;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public UploadedPerson getUploadedPerson() {
        return uploadedPerson;
    }

    public void setUploadedPerson(final UploadedPerson uploadedPerson) {
        this.uploadedPerson = uploadedPerson;
    }

    public LinkedinPerson getLinkedinPerson() {
        return linkedinPerson;
    }

    public void setLinkedinPerson(final LinkedinPerson linkedinPerson) {
        this.linkedinPerson = linkedinPerson;
    }
}
