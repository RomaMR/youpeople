package com.optigra.youpeople.services.user;

import com.optigra.youpeople.domain.organization.Organization;
import com.optigra.youpeople.domain.user.User;
import com.optigra.youpeople.domain.user.organization.UserOrganization;
import com.optigra.youpeople.exception.ContentNotFoundException;
import com.optigra.youpeople.exception.UserAlreadyExistsException;
import com.optigra.youpeople.persistence.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by oleh on 06.08.15.
 */
@Service("defaultUserService")
public class DefaultUserService implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserService.class);

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "bCryptPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(final User user) {
        LOGGER.info("DefaultUserService.save()");
        User savedUser = userRepository.save(user);
        LOGGER.info("DefaultUserService.save() finished");
        return savedUser;
    }

    @Override
    public void delete(final Long id) {
        LOGGER.info("DefaultUserService.delete(), id={}", id);
        userRepository.delete(id);
        LOGGER.info("DefaultUserService.delete() finished");
    }

    @Override
    public User findOne(final Long id) {
        LOGGER.info("DefaultUserService.findOne(), id={}", id);
        User user = userRepository.findOne(id);
        if (user == null) {
            LOGGER.error("User not found, id={}", id);
            throw new ContentNotFoundException("User not found");
        }
        LOGGER.info("DefaultUserService.findOne() finished");
        return user;
    }

    @Override
    public User findByLogin(final String email) {
        LOGGER.info("DefaultUserService.findByLogin(), email={}", email);
        User user = userRepository.findByLogin(email);

        if (user == null) {
            LOGGER.error("User not found, email={}", email);
            throw new ContentNotFoundException("User not found");
        }

        LOGGER.info("DefaultUserService.findByLogin() finished");
        return user;
    }

    @Override
    public void registration(final User user, final UserOrganization userOrganization, final Organization organization) {
        LOGGER.info("DefaultUserService.registration(), email={}", user.getLogin());
        User registeredUser = userRepository.findByLogin(user.getLogin());

        if (registeredUser != null) {
            throw new UserAlreadyExistsException(String.format("%s %s %s", "User with login", user.getLogin(), "already exists"));
        }

        userOrganization.setUser(user);
        userOrganization.setOrganization(organization);
        user.setUserOrganizations(new ArrayList<>());
        user.getUserOrganizations().add(userOrganization);
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        LOGGER.info("DefaultUserService.registration() finished");
    }
}
