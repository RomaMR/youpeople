package com.optigra.youpeople.converter.registration.organization;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.dto.RegistrationDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 13.10.15.
 */
@Component("defaultRegistrationDTOToOrganizationConverter")
public class DefaultRegistrationDTOToOrganizationConverter extends AbstractConverter<RegistrationDTO, Organization>{
    @Override
    public Organization convert(final RegistrationDTO registrationDTO, final Organization organization) {
        organization.setName(registrationDTO.getOrganization());
        return organization;
    }

    @Override
    public Organization convert(final RegistrationDTO registrationDTO) {
        return convert(registrationDTO, new Organization());
    }
}
