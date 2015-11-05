package com.optigra.youpeople.persistence.repository.volunteering;

import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.volunteering.Volunteering;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by romanmudryi on 15.08.15.
 */
public interface VolunteeringRepository extends JpaRepository<Volunteering, Long> {

    Long deleteByLinkedinPerson(LinkedinPerson linkedinPerson);

}
