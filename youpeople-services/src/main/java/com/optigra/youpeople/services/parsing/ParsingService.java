package com.optigra.youpeople.services.parsing;

import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import org.jsoup.nodes.Document;

import java.text.ParseException;
import java.util.List;

/**
 * Created by romanmudryi on 17.08.15.
 */
public interface ParsingService {

    Organization findOrganization(String organizationName);

    LinkedinPerson findPerson(String profileId, Document document);

    List<Education> findEducations(LinkedinPerson person, Document document) throws ParseException;

    List<Volunteering> findVolunteerings(LinkedinPerson person, Document document) throws ParseException;

    List<Job> findJobs(LinkedinPerson person, Document document) throws ParseException;

}
