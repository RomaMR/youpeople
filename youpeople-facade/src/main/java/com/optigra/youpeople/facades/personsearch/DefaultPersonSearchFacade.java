package com.optigra.youpeople.facades.personsearch;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.dto.PersonDTO;
import com.optigra.youpeople.services.organization.OrganizationService;
import com.optigra.youpeople.services.linkedinperson.LinkedinPersonService;
import com.optigra.youpeople.services.personsearch.PersonSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultPersonSearchFacade")
public class DefaultPersonSearchFacade implements PersonSearchFacade {
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonSearchFacade.class);

	@Resource(name = "defaultPersonSearchService")
	private PersonSearchService personSearchService;

	@Resource(name = "defaultLinkedinPersonService")
	private LinkedinPersonService personService;

	@Resource(name = "defaultOrganizationService")
	private OrganizationService organizationService;

	@Resource(name = "defaultPersonDTOConverter")
	private Converter<LinkedinPerson, PersonDTO> converter;

	@Override
	public Page<PersonDTO> searchByOrganizationName(String organizationName, String locality, String industry, String workStatus, Pageable pageable) {
		LOGGER.info("DefaultPersonSearchFacade.searchByOrganizationName(), organizationName={}", organizationName);
		personSearchService.searchByOrganizationName(organizationName);
		Organization organization = organizationService.findByName(organizationName);
		if (organization == null) {
			LOGGER.info("DefaultPersonSearchFacade.searchByOrganizationName() found no organization with name=\"{}\", so returning an empty list",
					organizationName);
			return new PageImpl<>(Collections.emptyList(), pageable, 0L);
		} else {
			Page<LinkedinPerson> page = personService.findByOrganization(organization, locality, industry, workStatus, pageable);
			LOGGER.info("DefaultPersonSearchFacade.searchByOrganizationName() finished");
			return new PageImpl<>(converter.convertAll(page.getContent()), pageable, page.getTotalElements());
		}
	}

}
