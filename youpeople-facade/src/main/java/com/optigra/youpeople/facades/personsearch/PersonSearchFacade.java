package com.optigra.youpeople.facades.personsearch;

import com.optigra.youpeople.dto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by romanmudryi on 07.08.15.
 */
public interface PersonSearchFacade {

	Page<PersonDTO> searchByOrganizationName(String organizationName, String locality, String industry, String workStatus, Pageable pageable);

}
