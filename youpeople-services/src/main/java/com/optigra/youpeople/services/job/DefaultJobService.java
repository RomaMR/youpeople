package com.optigra.youpeople.services.job;

import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.exception.ContentNotFoundException;
import com.optigra.youpeople.persistence.repository.job.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by oleh on 06.08.15.
 */
@Service("defaultJobService")
public class DefaultJobService implements JobService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJobService.class);

    @Resource(name = "jobRepository")
    private JobRepository jobRepository;

    @Override
    public Job save(final Job job) {
        LOGGER.info("DefaultJobService.save()");
        Job savedJob = jobRepository.save(job);
        LOGGER.info("DefaultJobService.save() finished");
        return savedJob;
    }

    @Override
    public void delete(final Long id) {
        LOGGER.info("DefaultJobService.delete(), id={}", id);
        jobRepository.delete(id);
        LOGGER.info("DefaultJobService.delete() finished");
    }

    @Override
    public Job findOne(final Long id) {
        LOGGER.info("DefaultJobService.findOne(), id={}", id);
        Job job = jobRepository.findOne(id);
        if (job == null) {
            LOGGER.error("Job not found, id={}", id);
            throw new ContentNotFoundException("Job not found");
        }
        LOGGER.info("DefaultJobService.delete() finished");
        return job;
    }
}
