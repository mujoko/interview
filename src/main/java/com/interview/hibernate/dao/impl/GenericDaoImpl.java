package com.interview.hibernate.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.interview.exception.DatabaseException;
import com.interview.hibernate.dao.GenericDao;

/**
 * Generic Data Access Object (DAO) Hibernate. This class is used to provide
 * generic methods to all DAOs.
 * 
 * @author mujoko
 * @param <ID>
 */
public class GenericDaoImpl<T, ID extends Serializable> extends
        HibernateDaoSupport implements GenericDao<T, Serializable> {
    
    /**
     */
    public static final String DB="DATABASE";
    

    /** Rawtypes constant. */
    private static final String RAWTYPES = "rawtypes";

    /** domain class. */
    @SuppressWarnings(RAWTYPES)
    private Class domainClass;

    /** class constructor. */
    @SuppressWarnings(RAWTYPES)
    public GenericDaoImpl() {
        this.setDomainClass((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * Save method. DO NOT CHANGE TO FINAL MODIFIER.
     * 
     * @param domain
     *            domain model.
     */
    @Transactional
    public void save(T domain) throws DatabaseException {
        try {
            getSession().save(domain);
            getSession().flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException("Db Error while save");
        }
    }

    /**
     * Save or update method.
     * 
     * @param domain
     *            domain model. throw new
     *            DatabaseException("Db Error while save or update");
     */
    public final void saveOrUpdate(final T domain) throws DatabaseException {
        try {
            getHibernateTemplate().saveOrUpdate(domain);
            getHibernateTemplate().flush();

        } catch (Exception e) {
            
            throw new DatabaseException("Db Error while save or update");
            
        }
    }

    /**
     * Update method.
     * 
     * @param domain
     *            domain model.
     */
    @Transactional
    public void update(final T domain) throws DatabaseException {
        try {
        	getHibernateTemplate().merge(domain);
        	getHibernateTemplate().flush();
        } catch (Exception e) {
        	e.printStackTrace();
            throw new DatabaseException("Db Error while save or update");
        }
    }

    /**
     * Delete method.
     * 
     * @param domain
     *            domain model.
     */
    @Transactional
    public void delete(final T domain) throws DatabaseException {

        try {
            getHibernateTemplate().delete(domain);
            getHibernateTemplate().flush();

        } catch (Exception e) {
        	e.printStackTrace();
            throw new DatabaseException("Db Error while delete");
                    
        }
    }

    /**
     * count method.
     * 
     * @return count
     */
    @SuppressWarnings(RAWTYPES)
    @Transactional(readOnly = true)
    public final Long count() throws DatabaseException {
        Long count = null;
        try {
            List list = getHibernateTemplate().find(
                    "select count(a.id) from " + getDomainClass().getName()
                            + " a ");
            count = (Long) list.get(0);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new DatabaseException("Db Error while save or update");
        }

        return count;
    }

    /**
     * @param id
     *            primary key
     * @return parameterQuery
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public T get(final Serializable id) throws DatabaseException {
        try {
            return (T) getHibernateTemplate().get(getDomainClass(), id);
        } catch (Exception e) {
            throw new DatabaseException("Db Error while get");

            
        }
    }

    /**
     * deleteAll implements multiple records deletion physically.
     * 
     * @param ids
     *            array of primary keys
     */
    @Transactional
    public final void deleteAll(final String[] ids) throws DatabaseException {
        try {
            int j = ids.length;
            for (int i = 0; i < j; i++) {
                String strId = ids[i].toString();
                T o = get(strId);
                delete(o);
            }

        } catch (Exception e) {
            throw new DatabaseException("Db Error while delete all");
        }
    }

    /**
     * @param aClass
     *            the domainClass to set
     */
    @SuppressWarnings(RAWTYPES)
    public final void setDomainClass(final Class aClass) {
        this.domainClass = aClass;
    }

    /**
     * @return the domainClass
     */
    @SuppressWarnings(RAWTYPES)
    public final Class getDomainClass() {
        return domainClass;
    }
    
    

}
