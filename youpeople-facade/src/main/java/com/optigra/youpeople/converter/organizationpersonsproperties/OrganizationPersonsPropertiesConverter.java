package com.optigra.youpeople.converter.organizationpersonsproperties;

import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.dto.OrganizationPersonsPropertiesDTO;

import java.util.List;

/**
 * Created by romanmudryi on 20.08.15.
 */
public interface OrganizationPersonsPropertiesConverter {


    OrganizationPersonsPropertiesDTO convert(Organization organization, List<Industry> industries, List<String> localities,
                                             OrganizationPersonsPropertiesDTO organizationPersonsPropertiesDTO);

    OrganizationPersonsPropertiesDTO convert(Organization organization, List<Industry> industries, List<String> localities);
}
