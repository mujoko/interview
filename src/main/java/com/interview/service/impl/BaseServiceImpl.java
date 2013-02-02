package com.interview.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.interview.exception.DatabaseException;
import com.interview.hibernate.dao.SearchDao;
import com.interview.service.BaseService;

/**
 * @author mujoko
 * @param <T>
 * @param <ID>
 */
public abstract class BaseServiceImpl<T, ID extends Serializable>  implements
        BaseService<T, Serializable> {

    /**
     * Create.
     * @param t
     *            need to be created.
     * @throws DatabaseException 
     */
    @Override
    @Transactional
    public void create(T t) throws DatabaseException {
        getDao().save(t);
    }

    /**
     * Update.
     * @param t
     *            Entity to update.
     * @throws DatabaseException 
     */
    @Override
    @Transactional
    public void update(T t) throws DatabaseException {
        getDao().update(t);

    }

    /**
     * @param id
     *            pk of Transaction.
     * @return Transaction
     * @throws DatabaseException 
     */
    @Override
    @Transactional(readOnly=true)
    public T get(Serializable id) throws DatabaseException {
        return getDao().get(id);
    }

    /**
     * @param id
     *            pk of Transaction.
     * @throws DatabaseException 
     */
    @Override
    @Transactional
    public void delete(String id) throws DatabaseException {
        getDao().delete(getDao().get(id));
    }
    
    @Override
    @Transactional
    public void delete(T t) throws DatabaseException {
        getDao().delete(t);
    }

    @Override
    public abstract SearchDao<T, Serializable> getDao();

    
    /**
     * find method.
     * @return {@link List}
     */
    @Transactional(readOnly = true)
    public final List<T> find() {
        return getDao().find();
    }
    
    /**
     * find method.
     *
     * @param start
     *            start record
     * @param limit
     *            limit record to be shown
     * @return {@link List}
     */
    @Transactional(readOnly = true)
    public final List<T> find(final int start, final int limit) {
        return getDao().find(null, start, limit, null, null);
    }

    /**
     * find method.
     *
     * @param parameters
     *            customize where clause
     * @param start
     *            start record
     * @param limit
     *            limit record to be shown
     * @param sort
     *            sort by
     * @param dir
     *            directions ascending / descending
     * @return {@link List}
     */
    @Transactional(readOnly = true)
    public final List<T> find(final String parameters,
            final int start, final int limit,
            final String sort, final String dir) {
        return getDao().find(parameters, start, limit, sort, dir);
    }
}
