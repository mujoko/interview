package com.interview.hibernate.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.interview.hibernate.dao.UserDao;
import com.interview.model.User;

/**
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl extends SearchDaoImpl<User, Serializable>
implements UserDao {

    /** construct log object to debug. */
    private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);

    /**
     * UserDaoImpl.
     *
     * @param sessionFactory
     *            to used for
     */
    @Autowired
    public UserDaoImpl(final SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    /**
     * Get.
     *
     * @param id
     *            id entity
     * @return User to used if exist
     */
    @Transactional(readOnly = true)
    public final User get(final Serializable id) {
        User user = (User) getHibernateTemplate().getSessionFactory()
        .getCurrentSession().get(User.class, id);
        return user;
    }

    /**
     * Get.
     *
     * @param id
     *            id entity
     * @return User to used if exist
     */
    @Transactional(readOnly = true)
    public final User getUserOnly(final Serializable id) {
        User user = (User) getHibernateTemplate().getSessionFactory()
        .getCurrentSession().get(User.class, id);

        return user;
    }





    /**
     * validateUser.
     * @param username
     *            username entity
     * @param password
     *            password
     * @return validate the user with password
     */
    @SuppressWarnings({ "unchecked" })
    @Transactional(readOnly = true)
    public final List<User> getCredentials(final String username,
            final String password) {
    	List<User> users=
         getHibernateTemplate()
        .getSessionFactory()
        .getCurrentSession()
        .createQuery(
                "from " + User.class.getName()
                +  " u where u.username = '" + username.trim()
                + "' and u.password = '" + password + "'").list();
    	return users;
    }

    /**
     * User credentials.
     *
     * @param username
     *            username entity
     * @return user credentials
     */
    @SuppressWarnings({ "unchecked" })
    @Transactional(readOnly = true)
    public  List<User> getCredentialsByUsername(final String username) {
    	LOG.info("getCredentialsByUsername");
    	List<User> result = getHibernateTemplate()
        .getSessionFactory()
        .getCurrentSession()
        .createQuery(
                "from " + User.class.getName()
                + " u  where u.username = '" + username + "'").list();
        return result;
    }

    /**
     * List method.
     *
     * @return list
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public final List<User> list() {
        getHibernateTemplate().flush();
        List<User> result = null;
        result = getHibernateTemplate().find(
                "from " + getDomainClass().getName());
        return result;
    }


    /**
     * @param domain
     *            domain model
     */
    @Transactional
    public final void updateLoginStatus(final User domain) {
        getHibernateTemplate().merge(domain);
        getHibernateTemplate().flush();
    }



}
