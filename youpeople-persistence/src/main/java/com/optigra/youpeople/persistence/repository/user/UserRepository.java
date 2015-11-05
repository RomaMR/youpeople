package com.optigra.youpeople.persistence.repository.user;

import com.optigra.youpeople.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by romanmudryi on 05.08.15.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);

}
