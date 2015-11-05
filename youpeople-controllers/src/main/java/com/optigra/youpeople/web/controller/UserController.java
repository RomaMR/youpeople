package com.optigra.youpeople.web.controller;

import com.optigra.youpeople.dto.RegistrationDTO;
import com.optigra.youpeople.dto.UserDTO;
import com.optigra.youpeople.facades.user.UserFacade;
import com.optigra.youpeople.web.commons.RMPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by romanmudryi on 04.08.15.
 */
@RestController
@RequestMapping(RMPath.User.ROOT)
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "defaultUserFacade")
    private UserFacade userFacade;

    @RequestMapping(value = RMPath.User.SESSION,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> session() {
        LOGGER.info("REST request to check if the current user is authenticated");

        UserDTO userDTO = userFacade.getUserFromSession();
        LOGGER.info("UserController.session() finished");
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = RMPath.User.REGISTRATION, method = RequestMethod.POST)
    public ResponseEntity<Void> registration(@Valid @RequestBody RegistrationDTO registrationDTO) {
        LOGGER.info("REST request for user registration");
        userFacade.registration(registrationDTO);
        LOGGER.info("UserController.session() finished");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
