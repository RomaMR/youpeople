package com.optigra.youpeople.services.personsearch;

import com.optigra.youpeople.domain.linkedinperson.LinkedinPerson;
import com.optigra.youpeople.exception.LinkedinSearchException;
import com.optigra.youpeople.persistence.repository.linkedinperson.LinkedinPersonRepository;
import com.optigra.youpeople.services.google.GoogleSearchService;
import com.optigra.youpeople.services.linkedin.LinkedinSearchService;
import com.optigra.youpeople.services.searchlog.SearchLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by romanmudryi on 10.08.15.
 */
@Service("defaultPersonSearchService")
public class DefaultPersonSearchService implements PersonSearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPersonSearchService.class);

    @Resource(name = "linkedinPersonRepository")
    private LinkedinPersonRepository personRepository;

    @Resource(name = "symbianGoogleSearchService")
    private GoogleSearchService googleSearchService;

    @Resource(name = "symbianLinkedinSearchService")
    private LinkedinSearchService linkedinSearchService;

    @Resource(name = "defaultSearchLogService")
    private SearchLogService searchLogService;

    @Override
    @Transactional
    public void searchByOrganizationName(String organizationName) {
        LOGGER.info("DefaultPersonSearchService.searchByOrganizationName(), organizationName={}", organizationName);
        
        // Counters for diagnostic purposes
        int successCount = 0;
        int failureCount = 0;
        int errorCount = 0;

        List<String> links = googleSearchService.getSearchResultLinks(organizationName + " site:linkedin.com/in", 0, 100);
        for (String link : links) {
            try {
                Optional<LinkedinPerson> person = linkedinSearchService.getPersonByLink(link);
                person.ifPresent(personRepository::save);
                if(person.isPresent()){
                	++successCount;
                }else{
                	++failureCount;
                }
            } catch (LinkedinSearchException e) {
                LOGGER.error("Failed to get a LinkedIn profile from address {}", link, e);
                ++errorCount;
            }
        }
        searchLogService.saveBySearchQuery(organizationName);
		LOGGER.info("DefaultPersonSearchService.searchByOrganizationName() finished, "
				+ "successCount={}, failureCount={}, errorCount={}", successCount, failureCount, errorCount);
    }

}
