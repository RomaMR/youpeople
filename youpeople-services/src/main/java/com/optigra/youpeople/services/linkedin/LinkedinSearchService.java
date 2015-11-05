package com.optigra.youpeople.services.linkedin;

import java.util.Optional;

import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;

/**
 * Created by romanmudryi on 25.08.15.
 */
public interface LinkedinSearchService {

    Optional<LinkedinPerson> getPersonByLink(String link);

}
