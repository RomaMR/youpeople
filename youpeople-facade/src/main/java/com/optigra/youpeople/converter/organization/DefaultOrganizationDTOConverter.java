package com.optigra.youpeople.converter.organization;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import com.optigra.youpeople.dto.EducationDTO;
import com.optigra.youpeople.dto.JobDTO;
import com.optigra.youpeople.dto.OrganizationDTO;
import com.optigra.youpeople.dto.VolunteeringDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultOrganizationDTOConverter")
public class DefaultOrganizationDTOConverter extends AbstractConverter<Organization, OrganizationDTO> {

    @Resource(name = "defaultJobDTOConverter")
    private Converter<Job, JobDTO> jobDTOConverter;

    @Resource(name = "defaultVolunteeringDTOConverter")
    private Converter<Volunteering, VolunteeringDTO> volunteeringDTOConverter;

    @Resource(name = "defaultEducationDTOConverter")
    private Converter<Education, EducationDTO> educationDTOConverter;

    @Override
    public OrganizationDTO convert(Organization organization, OrganizationDTO organizationDTO) {
        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        if (organization.getJobs() != null) {
            organizationDTO.setJobs(jobDTOConverter.convertAll(organization.getJobs()));
        }
        if (organization.getVolunteerings() != null) {
            organizationDTO.setVolunteerings(volunteeringDTOConverter.convertAll(organization.getVolunteerings()));
        }
        if (organization.getEducations() != null) {
            organizationDTO.setEducations(educationDTOConverter.convertAll(organization.getEducations()));
        }
        return organizationDTO;
    }

    @Override
    public OrganizationDTO convert(Organization organization) {
        return convert(organization, new OrganizationDTO());
    }
}
