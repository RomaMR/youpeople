package com.optigra.youpeople.persistence.repository.education;

import com.optigra.youpeople.domain.education.Education;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by romanmudryi on 15.08.15.
 */
public interface EducationRepository extends JpaRepository<Education, Long> {

    Long deleteByLinkedinPerson(LinkedinPerson linkedinPerson);

}
