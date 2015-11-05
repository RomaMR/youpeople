package com.optigra.youpeople.converter.user;

import com.optigra.youpeople.domain.user.User;
import com.optigra.youpeople.converter.AbstractConverter;
import com.optigra.youpeople.dto.UserDTO;
import org.springframework.stereotype.Component;

/**
 * Created by romanmudryi on 07.08.15.
 */
@Component("defaultUserDTOConverter")
public class DefaultUserDTOConverter extends AbstractConverter<User, UserDTO> {

    @Override
    public UserDTO convert(User user, UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }

    @Override
    public UserDTO convert(User user) {
        return convert(user, new UserDTO());
    }
}
