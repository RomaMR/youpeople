package com.optigra.youpeople.services.person;

import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;

/**
 * Created by romanmudryi on 18.10.15.
 */
public interface PersonService {
    void create(UploadedPerson uploadedPerson, LinkedinPerson linkedinPerson);
}
