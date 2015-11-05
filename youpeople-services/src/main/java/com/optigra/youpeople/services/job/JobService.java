package com.optigra.youpeople.services.job;

import com.optigra.youpeople.domain.job.Job;

/**
 * Created by oleh on 06.08.15.
 */
public interface JobService {
    Job save(Job job);

    void delete(Long id);

    Job findOne(Long id);
}
