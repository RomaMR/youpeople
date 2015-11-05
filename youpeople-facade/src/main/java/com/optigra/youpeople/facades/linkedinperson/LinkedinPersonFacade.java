package com.optigra.youpeople.facades.linkedinperson;

import com.optigra.youpeople.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by romanmudryi on 07.08.15.
 */
public interface LinkedinPersonFacade {
	
	PersonDTO getById(long id);

	Page<PersonDTO> getByOrganizationId(Long organizationId, String locality, String industry, String workStatus, Pageable pageable);

	Page<PersonDTO> getByOrganizationName(String organizationName, String locality, String industry, String workStatus, Pageable pageable);
}
