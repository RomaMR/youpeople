package com.optigra.youpeople.converter.registration.userorganization;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.user.organization.UserOrganization;
import com.optigra.youpeople.dto.RegistrationDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 14.10.15.
 */
@Component("defaultRegistrationDTOToUserOrganizationConverter")
public class DefaultRegistrationDTOToUserOrganizationConverter extends AbstractConverter<RegistrationDTO, UserOrganization>{
    @Override
    public UserOrganization convert(final RegistrationDTO registrationDTO, final UserOrganization userOrganization) {
        userOrganization.setFacebook(registrationDTO.getFacebook());
        userOrganization.setLinkedin(registrationDTO.getLinkedin());
        userOrganization.setPosition(registrationDTO.getPosition());
        return userOrganization;
    }

    @Override
    public UserOrganization convert(final RegistrationDTO registrationDTO) {
        return convert(registrationDTO, new UserOrganization());
    }
}
