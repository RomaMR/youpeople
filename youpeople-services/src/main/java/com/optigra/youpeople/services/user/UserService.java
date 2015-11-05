package com.optigra.youpeople.services.user;

import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.user.User;
import com.optigra.youpeople.domain.user.organization.UserOrganization;

/**
 * Created by oleh on 06.08.15.
 */
public interface UserService {
    User save(User user);

    void delete(Long id);

    User findOne(Long id);

    User findByLogin(String login);

    void registration(User user, UserOrganization userOrganization, Organization organization);
}
