package com.optigra.youpeople.services.linkedinperson;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.Predicate;
import com.optigra.youpeople.domain.audit.QExperience;
import com.optigra.youpeople.domain.education.QEducation;
import com.optigra.youpeople.domain.job.QJob;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.linkedinperson.QLinkedinPerson;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.volunteering.QVolunteering;
import com.optigra.youpeople.exception.ContentNotFoundException;
import com.optigra.youpeople.persistence.repository.linkedinperson.LinkedinPersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by oleh on 06.08.15.
 */
@Service("defaultLinkedinPersonService")
public class DefaultLinkedinPersonService implements LinkedinPersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLinkedinPersonService.class);

    @Resource(name = "linkedinPersonRepository")
    private LinkedinPersonRepository personRepository;

    @Override
    public LinkedinPerson save(LinkedinPerson person) {
        LOGGER.info("DefaultLinkedinPersonService.save()");
        LinkedinPerson savedPerson = personRepository.save(person);
        LOGGER.info("DefaultLinkedinPersonService.save() finished");
        return savedPerson;
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("DefaultLinkedinPersonService.delete(), id={}", id);
        personRepository.delete(id);
        LOGGER.info("DefaultLinkedinPersonService.delete() finished");
    }

    @Override
    public LinkedinPerson findOne(Long id) {
        LOGGER.info("DefaultLinkedinPersonService.findOne(), id={}", id);
        LinkedinPerson person = personRepository.findOne(id);
        if (person == null) {
            LOGGER.error("Person not found, id={}", id);
            throw new ContentNotFoundException("Person not found");
        }
        LOGGER.info("DefaultLinkedinPersonService.findOne() finished");
        return person;
    }

    @Override
    public Page<LinkedinPerson> findByOrganization(Organization organization, String locality,
                                           String industry, String workStatus, Pageable pageable) {
        LOGGER.info("DefaultLinkedinPersonService.findByOrganization(), organization={}, locality={}, industry={}, workStatus={}, ordering={}",
                organization, locality, industry, workStatus, pageable.getSort());
        locality = prepareParameter(locality);
        QLinkedinPerson person = QLinkedinPerson.linkedinPerson;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(person.locality.like(locality));
        if (StringUtils.isNotBlank(industry)) {
            builder.and(person.industry.name.eq(industry));
        }
        Predicate experiencePredicate = filterByExperience(person, organization.getId(), workStatus);
        builder.and(experiencePredicate);
        Predicate predicate = builder.getValue();
        LOGGER.info("DefaultLinkedinPersonService.findByOrganization() with predicate: ", predicate);
        Page<LinkedinPerson> persons = personRepository.findAll(predicate, pageable);

        if (persons.getNumberOfElements() == 0) {
            LOGGER.error("Persons not found, organization={}", organization);
            throw new ContentNotFoundException("Persons not found");
        }
        LOGGER.info("DefaultLinkedinPersonService.findByOrganization() finished");
        return persons;
    }

    @Override
    public Long countPersonByOrganizationAndWorkStatus(Long organizationId, String workStatus) {
        LOGGER.info("DefaultLinkedinPersonService.countPersonByOrganizationAndWorkStatus(), organizationId={}, workStatus={}", organizationId, workStatus);
        QLinkedinPerson person = QLinkedinPerson.linkedinPerson;
        BooleanBuilder builder = new BooleanBuilder();
        Predicate experiencePredicate = filterByExperience(person, organizationId, workStatus);
        builder.and(experiencePredicate);
        LOGGER.info("DefaultLinkedinPersonService.countPersonByOrganizationAndWorkStatus() finished");
        return personRepository.count(builder.getValue());
    }

    private Predicate filterByExperience(QLinkedinPerson person, Long organizationId, String workStatus) {
        BooleanBuilder experience = new BooleanBuilder();
        BooleanBuilder jobBooleanBuilder = new BooleanBuilder();
        BooleanBuilder volunteeringBooleanBuilder = new BooleanBuilder();
        BooleanBuilder educationBooleanBuilder = new BooleanBuilder();
        jobBooleanBuilder.and(QJob.job.linkedinPerson.id.eq(person.id)).and(QJob.job.organization.id.eq(organizationId));
        volunteeringBooleanBuilder.and(QVolunteering.volunteering.linkedinPerson.id.eq(person.id)).and(QVolunteering.volunteering.organization.id.eq
                (organizationId));
        educationBooleanBuilder.and(QEducation.education.linkedinPerson.id.eq(person.id)).and(QEducation.education.organization.id.eq(organizationId));
        if (StringUtils.isNotBlank(workStatus)) {
            jobBooleanBuilder.and(parseWorkStatus(workStatus, QJob.job._super));
            volunteeringBooleanBuilder.and(parseWorkStatus(workStatus, QVolunteering.volunteering._super));
            educationBooleanBuilder.and(parseWorkStatus(workStatus, QEducation.education._super));
        }
        experience
                .or(new JPASubQuery().from(QJob.job).where(jobBooleanBuilder).exists())
                .or(new JPASubQuery().from(QVolunteering.volunteering).where(volunteeringBooleanBuilder).exists())
                .or(new JPASubQuery().from(QEducation.education).where(educationBooleanBuilder).exists());
        return experience.getValue();
    }

    private Predicate parseWorkStatus(String workStatus, QExperience experience) {
        Predicate predicate;
        if (StringUtils.isBlank(workStatus)) {
            predicate = null;
        } else if (workStatus.startsWith("<")) {
            if (workStatus.length() > 1) {
                predicate = getFiredPredicate(experience, getStartFromWorkStatus(workStatus), getEndFromWorkStatus(workStatus));
            } else {
                predicate = experience.endDate.isNotNull();
            }
        } else if (workStatus.startsWith(">")) {
            if (workStatus.length() > 1) {
                predicate = getHiredPredicate(experience, getStartFromWorkStatus(workStatus), getEndFromWorkStatus(workStatus));
            } else {
                predicate = experience.endDate.isNull();
            }
        } else {
            predicate = getWorkPredicate(experience, getStartFromWorkStatus(workStatus), getEndFromWorkStatus(workStatus));
        }
        return predicate;
    }

    private int getStartFromWorkStatus(String workStatus) {
        String[] array;
        if (!StringUtils.isNumeric(String.valueOf(workStatus.toCharArray()[0]))) {
            array = workStatus.substring(1).split("-");
        } else {
            array = workStatus.split("-");
        }
        int start = -1;
        if ((array.length > 1) && (StringUtils.isNumeric(array[0]))) {
            start = Integer.parseInt(array[0]);
        }
        return start;
    }

    private int getEndFromWorkStatus(String workStatus) {
        String[] array = workStatus.substring(1).split("-");
        int end = -1;
        if ((array.length > 1) && (StringUtils.isNumeric(array[1]))) {
            end = Integer.parseInt(array[1]);
        } else if (StringUtils.isNumeric(array[0])) {
            end = Integer.parseInt(array[0]);
        }
        return end;
    }

    private Predicate getHiredPredicate(QExperience experience, int start, int end) {
        return experience.startDate.after(getYearAgo(end)).and(experience.startDate.before(getYearAgo(start)));
    }

    private Predicate getWorkPredicate(QExperience experience, int start, int end) {
        BooleanBuilder experienceBuilder = new BooleanBuilder();
        BooleanBuilder endDateBuilder = new BooleanBuilder();
        endDateBuilder.or(experience.endDate.after(getYearAgo(end)));
        endDateBuilder.or(experience.endDate.isNull());
        return experienceBuilder.and(experience.startDate.before(getYearAgo(start))).and(endDateBuilder);
    }

    private Predicate getFiredPredicate(QExperience experience, int start, int end) {
        return experience.endDate.before(getYearAgo(start)).and(experience.endDate.after(getYearAgo(end)));
    }

    private Date getYearAgo(int ago) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) - ago);
        return calendar.getTime();
    }

    private String prepareParameter(String parameter) {
        if (StringUtils.isNotBlank(parameter)) {
            parameter = "%" + parameter;
        } else {
            parameter = "%";
        }
        return parameter;
    }

}
