package com.optigra.youpeople.services.parsing;

import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import com.optigra.youpeople.persistence.repository.education.EducationRepository;
import com.optigra.youpeople.persistence.repository.industry.IndustryRepository;
import com.optigra.youpeople.persistence.repository.job.JobRepository;
import com.optigra.youpeople.persistence.repository.organization.OrganizationRepository;
import com.optigra.youpeople.persistence.repository.linkedinperson.LinkedinPersonRepository;
import com.optigra.youpeople.persistence.repository.volunteering.VolunteeringRepository;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by romanmudryi on 17.08.15.
 */
@Service("defaultParsingService")
public class DefaultParsingService implements ParsingService {

    private static final String TAG_HEADER = "header";

    private static final String TAG_HGROUP = "hgroup";

    private static final String TAG_SPAN = "span";

    private static final String EDUCATION_DATE = "education-date";

    private static final String START_MONTH = "January";

    @Resource(name = "linkedinPersonRepository")
    private LinkedinPersonRepository personRepository;

    @Resource(name = "organizationRepository")
    private OrganizationRepository organizationRepository;

    @Resource(name = "industryRepository")
    private IndustryRepository industryRepository;

    @Resource(name = "jobRepository")
    private JobRepository jobRepository;

    @Resource(name = "volunteeringRepository")
    private VolunteeringRepository volunteeringRepository;

    @Resource(name = "educationRepository")
    private EducationRepository educationRepository;

    @Transactional//TODO
    @Override
    public Organization findOrganization(String organizationName) {
    	List<Organization> orgsWithSimilarNames = organizationRepository.findByNameCaseInsensitive(organizationName);
        Organization organization = null;
        if(!orgsWithSimilarNames.isEmpty()){
        	organization = orgsWithSimilarNames.get(0);
        }
        if (organization == null) {
            organization = new Organization();
            organization.setName(organizationName);
            organization = organizationRepository.save(organization);
        }
        return organization;
    }

    @Override
    public LinkedinPerson findPerson(String profileId, Document document){
        LinkedinPerson person = personRepository.findByLinkedinProfileId(profileId);
        if (person == null) {
            person = new LinkedinPerson();
            person.setLinkedinProfileId(profileId);
        } else {
            jobRepository.deleteByLinkedinPerson(person);
            volunteeringRepository.deleteByLinkedinPerson(person);
            educationRepository.deleteByLinkedinPerson(person);
        }
        String fullName = document.select("[id=name] > h1 > span > span").text();
        String[] fullNameArray = fullName.split(" ");
        person.setFirstName(fullNameArray[0]);
        if ((fullNameArray.length > 1) && (StringUtils.isNotBlank(fullNameArray[1]))) {
            person.setLastName(fullNameArray[1]);
        }
        String locality = document.select("div[id=location] > dl > dd > span[class=locality]").text();
        person.setLocality(locality);
        String industryName = document.select("div[id=location] > dl > dd[class=industry]").text();
        Industry industry = industryRepository.findByName(industryName);
        person.setIndustry(industry);
        String numberOfConnections = document.select("div[class=profile-aux] > div[class=member-connections] > strong").text();
        if(numberOfConnections.length() > 3){
            numberOfConnections = numberOfConnections.substring(0, numberOfConnections.length()-1);
        }
        if(StringUtils.isNotBlank(numberOfConnections)) {
            person.setNumberOfConnections(Integer.parseInt(numberOfConnections));
        }
        String profilePicture = document.select("div[class=profile-picture] > a > img").attr("src");
        if(StringUtils.isNotBlank(profilePicture)) {
            person.setProfilePicture(profilePicture);
        }
        return person;
    }

    @Override
    public List<Education> findEducations(LinkedinPerson person, Document document) throws ParseException {
        List<Education> educations = new ArrayList<>();
        Elements divs = document.select("div[id=background-education] > div > div > div");
        for (Element div : divs) {
            Education education = new Education();
            for (Element element : div.children()) {
                if (TAG_HEADER.equals(element.tagName())) {
                    String[] degreeStrings = element.select("h5").text().split(", ");
                    if (StringUtils.isNotBlank(degreeStrings[0])) {
                        education.setDegree(degreeStrings[0]);
                    }
                    if ((degreeStrings.length > 1) && (!StringUtils.isNumeric(degreeStrings[1]))) {
                        education.setFieldOfStudy(degreeStrings[1]);
                    }
                    String organizationName = element.select("h4[dir=auto]").text();
                    Organization organization = findOrganization(organizationName);
                    education.setOrganization(organization);
                } else if ((TAG_SPAN.equals(element.tagName())) && EDUCATION_DATE.equals(element.attr("class")) && (education
                        .getStartDate() == null)) {
                    Date[] duration = findDuration(element.text());
                    education.setStartDate(duration[0]);
                    education.setEndDate(duration[1]);
                }
            }
            education.setLinkedinPerson(person);
            educations.add(education);
        }
        return educations;
    }

    @Override
    public List<Volunteering> findVolunteerings(LinkedinPerson person, Document document) throws ParseException {
        List<Volunteering> volunteerings = new ArrayList<>();
        Elements divs = document.select("div[id=background-volunteering] > div > div > div[class=experience]");
        for (Element div : divs) {
            Volunteering volunteering = new Volunteering();
            for (Element element : div.children()) {
                if (TAG_HGROUP.equals(element.tagName())) {
                    String position = element.select("h4 > span[dir=auto]").text();
                    volunteering.setCause(position);
                    String organizationName = element.select("h5 > span[dir=auto]").text();
                    Organization organization = findOrganization(organizationName);
                    volunteering.setOrganization(organization);
                } else if ((TAG_SPAN.equals(element.tagName())) && (volunteering.getStartDate() == null)) {
                    Date[] duration = findDuration(element.text());
                    volunteering.setStartDate(duration[0]);
                    volunteering.setEndDate(duration[1]);
                }
            }
            volunteering.setLinkedinPerson(person);
            volunteerings.add(volunteering);
        }
        return volunteerings;
    }

    @Override
    public List<Job> findJobs(LinkedinPerson person, Document document) throws ParseException {
        List<Job> jobs = new ArrayList<>();
        Elements divs = document.select("div[id=background-experience] > div > div");
        for (Element div : divs) {
            Job job = new Job();
            for (Element element : div.children()) {
                if (TAG_HEADER.equals(element.tagName())) {
                    String position = element.select("h4 > a").text();
                    job.setPosition(position);
                    String organizationName = element.select("[dir=auto]").text();
                    Organization organization = findOrganization(organizationName);
                    job.setOrganization(organization);
                } else if ((TAG_SPAN.equals(element.tagName())) && (job.getStartDate() == null)) {
                    Date[] duration = findDuration(element.text());
                    job.setStartDate(duration[0]);
                    job.setEndDate(duration[1]);
                }
            }
            job.setLinkedinPerson(person);
            jobs.add(job);
        }
        return jobs;
    }

    private Date[] findDuration(String dateString) throws ParseException {
        Date[] duration = new Date[2];
        String[] durationStrings = dateString.split(" – ");
        String[] start = durationStrings[0].split(" ");
        Date startDate = null;
        if (StringUtils.isNumeric(start[0])) {
            startDate = parseStringToDate(START_MONTH, start[0]);
        } else {
            if ((start.length > 1)) {
                if (StringUtils.isNumeric(start[1])) {
                    startDate = parseStringToDate(start[0], start[1]);
                } else if (start[1].length() >= 4) {
                    String year = start[1].substring(0, 4);
                    if (StringUtils.isNumeric(year)) {
                        startDate = parseStringToDate(start[0], year);
                    }
                }
            } else if (start[0].length() >= 4) {
                String year = start[0].substring(0, 4);
                if (StringUtils.isNumeric(year)) {
                    startDate = parseStringToDate(START_MONTH, year);
                }
            }
        }
        Date endDate = null;
        if (durationStrings.length > 1) {
            String[] end = durationStrings[1].split(" ");
            if (StringUtils.isNumeric(end[0])) {
                endDate = parseStringToDate(START_MONTH, end[0]);
            } else if (StringUtils.isNumeric(end[1])) {
                endDate = parseStringToDate(end[0], end[1]);
            }
        }
        duration[0] = startDate;
        duration[1] = endDate;
        return duration;
    }

    private Date parseStringToDate(String month, String year) throws ParseException {
        String format = "MMMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        return sdf.parse(month + " " + year);
    }

}
