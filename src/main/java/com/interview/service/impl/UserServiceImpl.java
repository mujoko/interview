package com.interview.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.interview.exception.DatabaseException;
import com.interview.hibernate.dao.UserDao;
import com.interview.model.User;
import com.interview.service.UserService;

/**
 * 
 * @author mujoko
 *
 */
@Service
public class UserServiceImpl implements UserService {

    /** log. */
    private static Logger log = Logger.getLogger(UserServiceImpl.class);


    /**
     * Inject userDao into service.
     */
    @Autowired
    private UserDao userDao;

    /**
     * @param user
     *            User need to be created.
     */
    @Transactional
    public final void create(final User user) {
        try {
            userDao.save(user);
            if (log.isDebugEnabled()) {
                log.debug("Saving User: " + user.getId());
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param parameters
     *            parameters.
     *@return list
     *
     */
    public final List<User> find(final String parameters) {
        return userDao.find(parameters, -1, -1, null, null);
    }

    /**
    *
    * @param parameters
    *            customize where clause.
    * @param start
    *            start record.
    * @param limit
    *            limit record to be shown.
    * @param sort
    *            sort by.
    * @param dir
    *            directions asc/desc.
    * @return list
    */
    public final List<User> find(final String parameters,
            final int start, final int limit, final String sort,
            final String dir) {
        return userDao.find(parameters, start, limit, sort, dir);
    }


    /**
     * @param id
     *            pk of User.
    * @return User
     */
    public final User get(final String id) {
        User user = null;
        try {
            user = userDao.get(id);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * @param id
     *            pk of User.
     */
    @Transactional
    public final void delete(final String id) {
        try {
            User user = userDao.get(id);
            userDao.delete(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param params
     *            params to count.
    * @return Long
     */
    public final Long count(final String params) {
        return userDao.count(params);
    }

    /**
     *    params to count.
     *    @return long.
     */
    public final Long count() {
        return userDao.count(null);
    }

    /**
     * @param user
     *            User need to be update.
     */
    @Transactional
    public final void update(final User user) {
        try {
            userDao.update(user);
            if (log.isDebugEnabled()) {
                log.debug("Saving User: " + user.getId());
            }
        } catch (DatabaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    

    /**
     * @param ids
     *            pk of Permission.
     */
    @Transactional
    public final void deleteAll(final String[] ids) {
        try {
            userDao.deleteAll(ids);
        } catch (DatabaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @return list
     */
    public final List<User> list() {
        return userDao.find();
    }
    
    public User getUser() {
        User u = new User();
        u.setId("090000");
        u.setName("A");

        return u;
    }

	@Override
	public String create(String username, String password, String firstName, String lastName) {
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			userDao.save(user);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return user.getId();
	}

	@Override
	public final  String authenticate(  String username,   String password) {
		List<User> users=find(" a.username='"+username+"' and a.password='"+password+"' ");
		if (users!=null && users.size()>0) {
			System.out.println("authenticate");
			return users.get(0).getId();
		}
		System.out.println(users);
		return null;
	}
    
    
}
