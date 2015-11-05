package com.optigra.youpeople.converter.job;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.dto.JobDTO;
import com.optigra.youpeople.dto.OrganizationDTO;

@Component("lazyPersonSpecificJobDTOConverter")
public class LazyPersonSpecificJobDTOConverter extends AbstractConverter<Job, JobDTO> {

    @Resource(name = "lazyOrganizationDTOConverter")
    private Converter<Organization, OrganizationDTO> organizationDTOConverter;

    @Override
    public JobDTO convert(Job job, JobDTO jobDTO) {
        jobDTO.setId(job.getId());
        jobDTO.setPosition(job.getPosition());
        jobDTO.setStartDate(job.getStartDate());
        jobDTO.setEndDate(job.getEndDate());
        jobDTO.setOrganization(organizationDTOConverter.convert(job.getOrganization()));
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
