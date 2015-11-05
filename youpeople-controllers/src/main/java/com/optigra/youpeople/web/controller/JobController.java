package com.optigra.youpeople.web.controller;

import com.optigra.youpeople.facades.job.JobFacade;
import com.optigra.youpeople.web.commons.RMPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by oleh on 06.08.15.
 */
@RestController
@RequestMapping(RMPath.Job.ROOT)
public class JobController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);

    @Resource(name = "defaultJobFacade")
    private JobFacade jobFacade;

}
