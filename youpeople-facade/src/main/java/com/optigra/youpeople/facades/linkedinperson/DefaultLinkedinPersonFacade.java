package com.optigra.youpeople.facades.linkedinperson;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.dto.JobDTO;
import com.optigra.youpeople.dto.PersonDTO;
import com.optigra.youpeople.facades.personsearch.DefaultPersonSearchFacade;
import com.optigra.youpeople.services.linkedinperson.LinkedinPersonService;
import com.optigra.youpeople.services.organization.OrganizationService;
import com.optigra.youpeople.services.uploadedperson.UploadedPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultLinkedinPersonFacade")
public class DefaultLinkedinPersonFacade implements LinkedinPersonFacade {

    @Resource(name = "defaultLinkedinPersonService")
    private LinkedinPersonService personService;
    
    @Resource(name = "defaultPersonDTOConverter")
    private Converter<LinkedinPerson, PersonDTO> personDTOConverter;
    
    @Resource(name = "defaultOrganizationService")
    private OrganizationService organizationService;

	@Resource(name = "defaultUploadedPersonService")
	private UploadedPersonService personExcelService;

	@Override
	public PersonDTO getById(long id) {
		return personDTOConverter.convert(personService.findOne(id));
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonSearchFacade.class);

	@Override
	public Page<PersonDTO> getByOrganizationId(Long organizationId, String locality, String industry, String workStatus, Pageable pageable) {
		LOGGER.info("DefaultLinkedinPersonFacade.getByOrganizationId(), organizationId={}", organizationId);
		Organization organization = organizationService.findOne(organizationId);
		if (organization == null) {
			LOGGER.info("DefaultLinkedinPersonFacade.getByOrganizationId() found no organization with id={}, so returning an empty list", organizationId);
			return new PageImpl<>(Collections.emptyList(), pageable, 0L);
		} else {
			Page<LinkedinPerson> page = personService.findByOrganization(organization, locality, industry, workStatus, pageable);
			LOGGER.info("DefaultPersonFacade.getByOrganizationId() finished");
			return new PageImpl<>(personDTOConverter.convertAll(page.getContent())
					.stream()
					.map(p -> {
						p.setQueriedCompanyLeaveDate(getLeaveDate(p, organization));
						return p;
					})
					.collect(Collectors.toList()), pageable, page.getTotalElements());
		}
	}

	@Override
	public Page<PersonDTO> getByOrganizationName(String organizationName, String locality, String industry, String workStatus, Pageable pageable) {
		LOGGER.info("DefaultLinkedinPersonFacade.getByOrganizationName(), organizationName={}", organizationName);
		Organization organization = organizationService.findByName(organizationName);
		if (organization == null) {
			LOGGER.info("DefaultLinkedinPersonFacade.getByOrganizationName() found no organization with name=\"{}\", so returning an empty list", organizationName);
			return new PageImpl<>(Collections.emptyList(), pageable, 0L);
		} else {
			Page<LinkedinPerson> page = personService.findByOrganization(organization, locality, industry, workStatus, pageable);
			LOGGER.info("DefaultPersonFacade.getByOrganizationName() finished");
			return new PageImpl<>(personDTOConverter.convertAll(page.getContent())
					.stream()
					.map(p -> {
						p.setQueriedCompanyLeaveDate(getLeaveDate(p, organization));
						return p;
					})
					.collect(Collectors.toList()), pageable, page.getTotalElements());
		}
	}
	
	// FIXME move this logic to service layer or even database
	// FIXME get rid of damn null-based logic
	private Date getLeaveDate(PersonDTO person, Organization organization){
		Long organizationId = organization.getId();
		JobDTO lastJobAtCompany = person.getJobs().stream()
				.filter(j -> (j.getOrganization().getId().equals(organizationId)))
				.reduce(null, (i, j) -> {
					if (i == null) return j;
					if (i.getStartDate().before(j.getStartDate())) return j;
					return i;
				});
		if(lastJobAtCompany == null){
			throw new IllegalArgumentException(String.format("Person %s never worked at Organization %s, so there can be no leave date", person, organization));
		} else {
			return lastJobAtCompany.getEndDate();
		}
		
	}
}
