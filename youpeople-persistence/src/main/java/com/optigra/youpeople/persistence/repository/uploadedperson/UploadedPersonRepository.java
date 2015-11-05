package com.optigra.youpeople.persistence.repository.uploadedperson;

import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by romanmudryi on 03.10.15.
 */
public interface UploadedPersonRepository extends JpaRepository<UploadedPerson, Long>, QueryDslPredicateExecutor<UploadedPerson> {

    UploadedPerson findByFirstNameAndLastName(String firstName, String LastName);

    UploadedPerson findByPersonalEmail(String personalEmail);
}
