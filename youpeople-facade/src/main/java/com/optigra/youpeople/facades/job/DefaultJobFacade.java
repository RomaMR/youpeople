package com.optigra.youpeople.facades.job;

import com.optigra.youpeople.services.job.JobService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultJobFacade")
public class DefaultJobFacade implements JobFacade{

    @Resource(name = "defaultJobService")
    private JobService jobService;

}
