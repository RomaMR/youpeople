package com.optigra.youpeople.facades.organization;

import com.optigra.youpeople.dto.OrganizationDTO;
import com.optigra.youpeople.dto.OrganizationPersonsPropertiesDTO;

import java.util.List;

/**
 * Created by romanmudryi on 07.08.15.
 */
public interface OrganizationFacade {

    OrganizationPersonsPropertiesDTO findPersonsProperties(final Long organizationId);

    List<OrganizationDTO> getUserOrganizations();
}
