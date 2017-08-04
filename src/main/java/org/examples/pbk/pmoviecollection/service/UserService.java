package org.examples.pbk.pmoviecollection.service;

import org.examples.pbk.pmoviecollection.model.User;

import java.util.List;

/**
 * Created by Vitalik on 03.08.2017.
 */
public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    void deleteAllUsers();

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
