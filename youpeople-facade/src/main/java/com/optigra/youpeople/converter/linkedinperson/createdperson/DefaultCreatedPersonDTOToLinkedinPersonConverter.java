package com.optigra.youpeople.converter.linkedinperson.createdperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import com.optigra.youpeople.dto.createperson.CreatePersonDTO;
import com.optigra.youpeople.dto.createperson.CreatedPersonEducationDTO;
import com.optigra.youpeople.dto.createperson.CreatedPersonJobDTO;
import com.optigra.youpeople.dto.createperson.CreatedPersonVolunteeringDTO;
import com.optigra.youpeople.persistence.repository.industry.IndustryRepository;
import com.optigra.youpeople.persistence.repository.organization.OrganizationRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by romanmudryi on 18.10.15.
 */
@Component("defaultCreatedPersonDTOToLinkedinPersonConverter")
public class DefaultCreatedPersonDTOToLinkedinPersonConverter extends AbstractConverter<CreatePersonDTO, LinkedinPerson> {

    @Resource(name = "industryRepository")
    private IndustryRepository industryRepository;

    @Resource(name = "organizationRepository")
    private OrganizationRepository organizationRepository;

    @Resource(name = "createdPersonEducationDTOToEducationConverter")
    private Converter<CreatedPersonEducationDTO, Education> createdPersonEducationDTOToEducationConverter;

    @Resource(name = "createdPersonJobDTOToJobConverter")
    private Converter<CreatedPersonJobDTO, Job> createdPersonJobDTOToJobConverter;

    @Resource(name = "createdPersonVolunteeringDTOToVolunteeringConverter")
    private Converter<CreatedPersonVolunteeringDTO, Volunteering> createdPersonVolunteeringDTOToVolunteeringConverter;

    @Override
    public LinkedinPerson convert(final CreatePersonDTO createPersonDTO, final LinkedinPerson linkedinPerson) {
        linkedinPerson.setFirstName(createPersonDTO.getFirstName());
        linkedinPerson.setLastName(createPersonDTO.getLastName());
        linkedinPerson.setLinkedinProfileId(createPersonDTO.getLinkedinProfileId());
        linkedinPerson.setLocality(createPersonDTO.getLocality());
        linkedinPerson.setLinkedinProfileId(createPersonDTO.getLinkedinProfileId());
        if (createPersonDTO.getIndustry() != null) {
            Industry industry = industryRepository.findByName(createPersonDTO.getIndustry());
            linkedinPerson.setIndustry(industry);
        }
        Organization organization = null;
        if (createPersonDTO.getOrganizationId() != null) {
            organization = organizationRepository.findOne(createPersonDTO.getOrganizationId());
        }
        if (createPersonDTO.getJobs() != null) {
            List<Job> jobs = new ArrayList<>();
            for (CreatedPersonJobDTO createdPersonJobDTO : createPersonDTO.getJobs()) {
                Job job = createdPersonJobDTOToJobConverter.convert(createdPersonJobDTO);
                job.setOrganization(organization);
                job.setLinkedinPerson(linkedinPerson);
                jobs.add(job);
            }
        }
        if (createPersonDTO.getEducations() != null) {
            List<Education> educations = new ArrayList<>();
            for (CreatedPersonEducationDTO createdPersonEducationDTO : createPersonDTO.getEducations()) {
                Education education = createdPersonEducationDTOToEducationConverter.convert(createdPersonEducationDTO);
                education.setOrganization(organization);
                education.setLinkedinPerson(linkedinPerson);
                educations.add(education);
            }
        }
        if (createPersonDTO.getVolunteerings() != null) {
            List<Volunteering> volunteerings = new ArrayList<>();
            for (CreatedPersonVolunteeringDTO createdPersonVolunteeringDTO : createPersonDTO.getVolunteerings()) {
                Volunteering volunteering = createdPersonVolunteeringDTOToVolunteeringConverter.convert(createdPersonVolunteeringDTO);
                volunteering.setOrganization(organization);
                volunteering.setLinkedinPerson(linkedinPerson);
                volunteerings.add(volunteering);
            }
        }
        return linkedinPerson;
    }

    @Override
    public LinkedinPerson convert(final CreatePersonDTO createPersonDTO) {
        return convert(createPersonDTO, new LinkedinPerson());
    }
}
