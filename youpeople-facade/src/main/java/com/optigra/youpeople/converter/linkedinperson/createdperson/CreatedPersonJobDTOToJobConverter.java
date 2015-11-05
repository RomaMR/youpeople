package com.optigra.youpeople.converter.linkedinperson.createdperson;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.dto.createperson.CreatedPersonJobDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 19.10.15.
 */
@Component("createdPersonJobDTOToJobConverter")
public class CreatedPersonJobDTOToJobConverter extends AbstractConverter<CreatedPersonJobDTO, Job> {

    @Override
    public Job convert(final CreatedPersonJobDTO jobDTO, final Job job) {
        job.setPosition(jobDTO.getPosition());
        return job;
    }

    @Override
    public Job convert(final CreatedPersonJobDTO jobDTO) {
        return convert(jobDTO, new Job());
    }
}
