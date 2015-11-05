package com.optigra.youpeople.converter.organizationpersonsproperties;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.industry.Industry;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.dto.IndustryDTO;
import com.optigra.youpeople.dto.OrganizationPersonsPropertiesDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by romanmudryi on 20.08.15.
 */
@Component("defaultOrganizationPersonsPropertiesConverter")
public class DefaultOrganizationPersonsPropertiesConverter implements OrganizationPersonsPropertiesConverter{

    @Resource(name = "lazyIndustryDTOConverter")
    private Converter<Industry, IndustryDTO> industryDTOConverter;

    @Override
    public OrganizationPersonsPropertiesDTO convert(Organization organization, List<Industry> industries, List<String> localities,
                                                    OrganizationPersonsPropertiesDTO organizationPersonsPropertiesDTO){
        organizationPersonsPropertiesDTO = new OrganizationPersonsPropertiesDTO();
        organizationPersonsPropertiesDTO.setOrganizationId(organization.getId());
        organizationPersonsPropertiesDTO.setOrganizationName(organization.getName());
        organizationPersonsPropertiesDTO.setLocalities(localities);
        if(industries != null) {
            organizationPersonsPropertiesDTO.setIndustries(industryDTOConverter.convertAll(industries));
        }
        return organizationPersonsPropertiesDTO;
    }

    @Override
    public OrganizationPersonsPropertiesDTO convert(Organization organization, List<Industry> industries, List<String> localities){
        return convert(organization, industries, localities, new OrganizationPersonsPropertiesDTO());
    }

}
