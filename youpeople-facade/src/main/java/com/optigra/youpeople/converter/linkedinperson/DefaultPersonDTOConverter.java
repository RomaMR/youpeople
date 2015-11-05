package com.optigra.youpeople.converter.linkedinperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import com.optigra.youpeople.dto.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultPersonDTOConverter")
public class DefaultPersonDTOConverter extends AbstractConverter<LinkedinPerson, PersonDTO> {

	@Resource(name = "lazyPersonSpecificJobDTOConverter")
    private Converter<Job, JobDTO> jobDTOConverter;

    @Resource(name = "defaultVolunteeringDTOConverter")
    private Converter<Volunteering, VolunteeringDTO> volunteeringDTOConverter;

    @Resource(name = "defaultEducationDTOConverter")
    private Converter<Education, EducationDTO> educationDTOConverter;

    @Resource(name = "lazyIndustryDTOConverter")
    private Converter<Industry, IndustryDTO> industryDTOConverter;

    @Override
    public PersonDTO convert(LinkedinPerson person, PersonDTO personDTO) {
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setPersonalEmail(person.getPersonalEmail());
        personDTO.setWorkEmail(person.getWorkEmail());
        personDTO.setLinkedinProfileId(person.getLinkedinProfileId());
        personDTO.setLocality(person.getLocality());
        personDTO.setNumberOfConnections(person.getNumberOfConnections());
        personDTO.setProfilePicture(person.getProfilePicture());
        if (person.getIndustry() != null) {
            personDTO.setIndustry(industryDTOConverter.convert(person.getIndustry()));
        }
        List<Job> jobs = person.getJobs();
        if (jobs != null) {
            personDTO.setJobs(jobDTOConverter.convertAll(jobs));
            if(!jobs.isEmpty()){
            	personDTO.setCurrentJob(jobDTOConverter.convert(lastOf(jobs)));
            }
        }
        if (person.getVolunteerings() != null) {
            personDTO.setVolunteerings(volunteeringDTOConverter.convertAll(person.getVolunteerings()));
        }
        if (person.getEducations() != null) {
            personDTO.setEducations(educationDTOConverter.convertAll(person.getEducations()));
        }
        return personDTO;
    }

    @Override
    public PersonDTO convert(LinkedinPerson person) {
        return convert(person, new PersonDTO());
    }
    
    private Job lastOf(List<Job> jobs){
    	return jobs.stream().filter( j -> (j.getEndDate() == null)).findAny().get();
    }
}
