package com.optigra.youpeople.web.controller;

import com.optigra.youpeople.dto.PersonDTO;
import com.optigra.youpeople.dto.UploadedPersonDTO;
import com.optigra.youpeople.dto.createperson.CreatePersonDTO;
import com.optigra.youpeople.facades.linkedinperson.LinkedinPersonFacade;
import com.optigra.youpeople.facades.person.PersonFacade;
import com.optigra.youpeople.facades.personsearch.PersonSearchFacade;
import com.optigra.youpeople.facades.uploadedperson.UploadedPersonFacade;
import com.optigra.youpeople.web.commons.RMPath;
import com.optigra.youpeople.web.pagination.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
@RestController
@RequestMapping(RMPath.Person.ROOT)
public class PersonController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@Resource(name = "personPaginationUtil")
	private PaginationUtil paginationUtil;

	@Resource(name = "defaultPersonFacade")
	private PersonFacade personFacade;

	@Resource(name = "defaultLinkedinPersonFacade")
	private LinkedinPersonFacade linkedinPersonFacade;

	@Resource(name = "defaultUploadedPersonFacade")
	private UploadedPersonFacade uploadedPersonFacade;

	@Resource(name = "defaultPersonSearchFacade")
	private PersonSearchFacade personSearchFacade;

	@RequestMapping(value = "{personId}", method = RequestMethod.GET)
	public PersonDTO getById(@PathVariable("personId") long personId) {
		LOGGER.info("PersonController.getById(), personId={}", personId);
		PersonDTO person = linkedinPersonFacade.getById(personId);
		LOGGER.info("PersonController.getById() finished");
		return person;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody CreatePersonDTO createPersonDTO) {
		LOGGER.info("PersonController.create()");
		personFacade.create(createPersonDTO);
		LOGGER.info("PersonController.create() finished");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = RMPath.Person.SEARCH, method = RequestMethod.GET)
	public ResponseEntity<List<PersonDTO>> searchByOrganizationName(@RequestParam("organizationName") final String organizationName,
																	@RequestParam(value = "order", required = false) final String order,
																	@RequestParam(value = "locality", required = false) final String locality,
																	@RequestParam(value = "industry", required = false) final String industry,
																	@RequestParam(value = "workStatus", required = false) final String workStatus,
																	@RequestParam(value = "page", required = false) final Integer offset,
																	@RequestParam(value = "limit", required = false) final Integer limit) throws URISyntaxException {
		LOGGER.info("PersonController.searchByOrganizationName(), organizationName={}", organizationName);
		Pageable pageable = paginationUtil.generatePageRequest(offset, limit, order);
		Page<PersonDTO> page = personSearchFacade.searchByOrganizationName(organizationName, locality, industry, workStatus, pageable);
		HttpHeaders headers = paginationUtil.generatePaginationHttpHeaders(page);
		LOGGER.info("PersonController.searchByOrganizationName() finished");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}


	@RequestMapping(value = "/organization/{organizationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonDTO>> findByOrganizationId(
			@PathVariable("organizationId") final Long organizationId,
			@RequestParam(value = "order", required = false) final String order,
			@RequestParam(value = "locality", required = false) final String locality,
			@RequestParam(value = "industry", required = false) final String industry,
			@RequestParam(value = "workStatus", required = false) final String workStatus,
			@RequestParam(value = "page", required = false) final Integer offset,
			@RequestParam(value = "limit", required = false) final Integer limit) throws URISyntaxException {
		LOGGER.info("PersonController.getByOrganizationName(), organizationId={}", organizationId);
		Pageable pageable = paginationUtil.generatePageRequest(offset, limit, order);
		Page<PersonDTO> page = linkedinPersonFacade.getByOrganizationId(organizationId, locality, industry, workStatus, pageable);
		HttpHeaders headers = paginationUtil.generatePaginationHttpHeaders(page);
		LOGGER.info("PersonController.getByOrganizationName() finished");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/organization/{organizationId}/upload", method = RequestMethod.GET)
	public ResponseEntity<List<UploadedPersonDTO>> getUpploadedPersons(@PathVariable("organizationId") Long organizationId,
													  @RequestParam(value = "order", required = false) final String order,
													  @RequestParam(value = "page", required = false) final Integer offset,
													  @RequestParam(value = "limit", required = false) final Integer limit) throws URISyntaxException {
		LOGGER.info("PersonController.getByOrganizationName(), organizationId={}", organizationId);
		Pageable pageable = paginationUtil.generatePageRequest(offset, limit, order);
		Page<UploadedPersonDTO> page = uploadedPersonFacade.searchByOrganizationId(organizationId, pageable);
		HttpHeaders headers = paginationUtil.generatePaginationHttpHeaders(page);
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/organization/{organizationId}/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadPersons(@PathVariable("organizationId") Long organizationId,
												@RequestParam("file") MultipartFile multipartFile) {
		LOGGER.info("PersonController.uploadPersons(), organizationId={}", organizationId);
		String message = uploadedPersonFacade.uploadPerson(organizationId, multipartFile);
		LOGGER.info("PersonController.uploadPersons() finished");
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

}
