package com.optigra.youpeople.persistence.repository.person;

import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.person.Person;
import com.optigra.youpeople.domain.uploadedperson.UploadedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Created by romanmudryi on 03.10.15.
 */
public interface PersonRepository extends JpaRepository<Person, Long>, QueryDslPredicateExecutor<Person> {

    Person findByUploadedPersonAndLinkedinPerson(UploadedPerson uploadedPerson, LinkedinPerson linkedinPerson);

}
