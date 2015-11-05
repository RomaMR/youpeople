package com.optigra.youpeople.services.linkedinperson;

import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by oleh on 06.08.15.
 */
public interface LinkedinPersonService {
    LinkedinPerson save(LinkedinPerson person);

    void delete(Long id);

    LinkedinPerson findOne(Long id);

    Page<LinkedinPerson> findByOrganization(Organization organization, String locality, String industry, String workStatus, Pageable pageable);

    Long countPersonByOrganizationAndWorkStatus(Long organizationId, String workStatus);
}
