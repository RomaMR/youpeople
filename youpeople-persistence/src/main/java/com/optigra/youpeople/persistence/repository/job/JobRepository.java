package com.optigra.youpeople.persistence.repository.job;

import com.optigra.youpeople.domain.job.Job;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by romanmudryi on 05.08.15.
 */
public interface JobRepository extends JpaRepository<Job,Long>, QueryDslPredicateExecutor<Job> {

    Long deleteByLinkedinPerson(LinkedinPerson linkedinPerson);

}
