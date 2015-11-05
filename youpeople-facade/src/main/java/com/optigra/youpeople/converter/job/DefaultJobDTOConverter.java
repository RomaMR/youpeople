package com.optigra.youpeople.converter.job;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.dto.JobDTO;
import com.optigra.youpeople.dto.OrganizationDTO;
import com.optigra.youpeople.dto.PersonDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultJobDTOConverter")
public class DefaultJobDTOConverter extends AbstractConverter<Job, JobDTO> {

    @Resource(name = "lazyOrganizationDTOConverter")
    private Converter<Organization, OrganizationDTO> organizationDTOConverter;

    @Resource(name = "lazyPersonDTOConverter")
    private Converter<LinkedinPerson, PersonDTO> personDTOConverter;

    @Override
    public JobDTO convert(Job job, JobDTO jobDTO) {
        jobDTO.setId(job.getId());
        jobDTO.setPosition(job.getPosition());
        jobDTO.setStartDate(job.getStartDate());
        jobDTO.setEndDate(job.getEndDate());
        jobDTO.setOrganization(organizationDTOConverter.convert(job.getOrganization()));
        jobDTO.setPerson(personDTOConverter.convert(job.getLinkedinPerson()));
        return jobDTO;
    }

    @Override
    public JobDTO convert(Job job) {
        return convert(job, new JobDTO());
    }

    @Override
    public List<JobDTO> convertAll(List<Job> jobs) {
        return super.convertAll(jobs);
    }

}
