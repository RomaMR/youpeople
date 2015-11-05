package com.optigra.youpeople.converter.organization;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.dto.OrganizationDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 13.08.15.
 */
@Component("lazyOrganizationDTOConverter")
public class LazyOrganizationDTOConverter extends AbstractConverter<Organization, OrganizationDTO>{

    @Override
    public OrganizationDTO convert(Organization organization, OrganizationDTO organizationDTO) {
        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        return organizationDTO;
    }

    @Override
    public OrganizationDTO convert(Organization organization) {
        return convert(organization, new OrganizationDTO());
    }
}


