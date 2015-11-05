package com.optigra.youpeople.converter.registration.user;

import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.domain.user.User;
import com.optigra.youpeople.dto.RegistrationDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 13.10.15.
 */
@Component("defaultRegistrationDTOToUserConverter")
public class DefaultRegistrationDTOToUserConverter extends AbstractConverter<RegistrationDTO, User> {
    @Override
    public User convert(final RegistrationDTO registrationDTO, final User user) {
        user.setFirstName(registrationDTO.getFirstName());
        user.setLastName(registrationDTO.getLastName());
        user.setLogin(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());
        user.setPhone(registrationDTO.getPhone());
        return user;
    }

    @Override
    public User convert(final RegistrationDTO registrationDTO) {
        return convert(registrationDTO, new User());
    }
}
