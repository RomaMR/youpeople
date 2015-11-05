package com.optigra.youpeople.facades.organization;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.converter.organizationpersonsproperties.OrganizationPersonsPropertiesConverter;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.dto.OrganizationDTO;
import com.optigra.youpeople.dto.OrganizationPersonsPropertiesDTO;
import com.optigra.youpeople.services.organization.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultOrganizationFacade")
public class DefaultOrganizationFacade implements OrganizationFacade{
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultOrganizationFacade.class);

    @Resource(name = "defaultOrganizationService")
    private OrganizationService organizationService;

    @Resource(name = "defaultOrganizationPersonsPropertiesConverter")
    private OrganizationPersonsPropertiesConverter organizationPersonsPropertiesConverter;
    
    @Resource(name = "lazyOrganizationDTOConverter")
    private Converter<Organization, OrganizationDTO> organizationDTOConverter;

    @Override
    public OrganizationPersonsPropertiesDTO findPersonsProperties(Long organizationId) {
        LOGGER.info("DefaultOrganizationFacade.findPersonsProperties(), organizationId={}", organizationId);
        Organization organization = organizationService.findOne(organizationId);
        List<Industry> industries = organizationService.findPersonsIndustries(organization);
        List<String> localities = organizationService.findPersonsLocalities(organization);
        OrganizationPersonsPropertiesDTO organizationPersonsPropertiesDTO =
                organizationPersonsPropertiesConverter.convert(organization, industries, localities);
        LOGGER.info("DefaultOrganizationFacade.findPersonsProperties() finished");
        return organizationPersonsPropertiesDTO;
    }

	@Override
	public List<OrganizationDTO> getUserOrganizations() {
		LOGGER.info("DefaultOrganizationFacade.getUserOrganizations()");
        List<Organization> organizations = organizationService.getUserOrganizations();
        List<OrganizationDTO> organizationDTOs = organizationDTOConverter.convertAll(organizations);
        LOGGER.info("DefaultOrganizationFacade.getUserOrganizations() finished");
        return organizationDTOs;
	}
}
