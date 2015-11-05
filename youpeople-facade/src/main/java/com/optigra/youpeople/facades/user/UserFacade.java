package com.optigra.youpeople.facades.user;

import com.optigra.youpeople.dto.RegistrationDTO;
import com.optigra.youpeople.dto.UserDTO;

/**
 * Created by romanmudryi on 07.08.15.
 */
public interface UserFacade {

    UserDTO getUserFromSession();

    void registration(RegistrationDTO registrationDTO);
}
