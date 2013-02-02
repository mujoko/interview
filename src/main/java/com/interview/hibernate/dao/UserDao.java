package com.interview.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.interview.model.User;

/**
 * This class is an interface of User DAO.
 *
 */
public interface UserDao extends SearchDao<User, Serializable> {



    /**
     * @param username
     *            username of user.
     * @return List<User>
     */
    List<User> getCredentialsByUsername(String username);

    /**
     * @param username
     *            username of user.
     * @param password
     *            password of user.
     * @return List<User>
     */
    List<User> getCredentials(String username, String password);

    /**
     * @param domain
     *            user role or login status.
     */
    void updateLoginStatus(User domain);


}
