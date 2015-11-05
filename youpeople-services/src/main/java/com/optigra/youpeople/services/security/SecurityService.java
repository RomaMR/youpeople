package com.optigra.youpeople.services.security;

import com.optigra.youpeople.domain.user.User;

/**
 * Created by romanmudryi on 07.08.15.
 */
public interface SecurityService {

    User getCurrentUser();

    boolean isUserAuthenticated();

    boolean isUserInRole(String role);

}
