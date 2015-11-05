package com.optigra.youpeople.services.organization;

import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.organization.Organization;

import java.util.List;

/**
 * Created by oleh on 06.08.15.
 */
public interface OrganizationService {

    Organization save(Organization organization);

    void delete(Long id);

    Organization findOne(Long id);
    
    Organization findByName(String organizationName);

    List<String> findPersonsLocalities(Organization organization);

    List<Industry> findPersonsIndustries(Organization organization);

	List<Organization> findAll();
	
	List<Organization> findAllSearched();

    Organization registration(Organization organization);

    List<Organization> getUserOrganizations();
}
