package com.optigra.youpeople.facades.user;

import com.optigra.youpeople.converter.Converter;
import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.user.User;
import com.optigra.youpeople.domain.user.organization.UserOrganization;
import com.optigra.youpeople.dto.RegistrationDTO;
import com.optigra.youpeople.dto.UserDTO;
import com.optigra.youpeople.services.organization.OrganizationService;
import com.optigra.youpeople.services.security.SecurityService;
import com.optigra.youpeople.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultUserFacade")
public class DefaultUserFacade implements UserFacade{
    public static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserFacade.class);

    @Resource(name = "defaultUserService")
    private UserService userService;

    @Resource(name = "defaultSecurityService")
    private SecurityService securityService;

    @Resource(name = "defaultOrganizationService")
    private OrganizationService organizationService;

    @Resource(name = "defaultUserDTOConverter")
    private Converter<User, UserDTO> userDTOConverter;

    @Resource(name = "defaultRegistrationDTOToUserConverter")
    private Converter<RegistrationDTO, User> registrationDTOToUserConverter;

    @Resource(name = "defaultRegistrationDTOToUserOrganizationConverter")
    private Converter<RegistrationDTO, UserOrganization> registrationDTOToUserOrganizationConverter;

    @Resource(name = "defaultRegistrationDTOToOrganizationConverter")
    private Converter<RegistrationDTO, Organization> registrationDTOToOrganizationConverter;

    public UserDTO getUserFromSession(){
        LOGGER.info("DefaultUserFacade.getUserFromSession");
        User user = securityService.getCurrentUser();
        LOGGER.info("DefaultUserFacade.getUserFromSession finished");
        return userDTOConverter.convert(user);
    }

    @Override
    public void registration(final RegistrationDTO registrationDTO) {
        LOGGER.info("DefaultUserFacade.registration");
        User user = registrationDTOToUserConverter.convert(registrationDTO);
        UserOrganization userOrganization = registrationDTOToUserOrganizationConverter.convert(registrationDTO);
        Organization organization = registrationDTOToOrganizationConverter.convert(registrationDTO);
        organization = organizationService.registration(organization);
        userService.registration(user, userOrganization, organization);
        LOGGER.info("DefaultUserFacade.registration finished");
    }

}
