package com.interview.service;

import java.io.Serializable;
import java.util.List;

import com.interview.exception.DatabaseException;
import com.interview.hibernate.dao.GenericDao;

/**
 * BaseService Interface for standard CRUD type.
 *
 * @authormujoko
 *
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable>  {
    /**
     * Create.
     * @param t need to be created.
     */
    void create(T t)throws DatabaseException;

    /**
     * Update.
     * @param t Entity to update.
     */
    void update(T t)throws DatabaseException;

    /**
     * @param id
     *            pk of Transaction.
     * @return Transaction
     */
    T get(ID id)throws DatabaseException;

    /**
     * @param id
     *            pk of Transaction.
     */
    void delete(String id)throws DatabaseException;
    /**
     *
     * @param t
     * @param t Entity to delete.
     */
    void delete(T t)throws DatabaseException;


    /**
     * @return
     */
    GenericDao<T, Serializable> getDao()throws DatabaseException;

    /**
     * find method.
     * @return list
     */
    List<T> find()throws DatabaseException;

    /**
     * find method.
     *
     * @param start
     *            start record
     * @param limit
     *            limit record to be shown
     * @return list
     */
    List<T> find(final int start, final int limit)throws DatabaseException;

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
     *            directions asc/desc
     * @return list
     */
    List<T> find(final String parameters,
            final int start, final int limit,
            final String sort, final String dir)throws DatabaseException;
    
}
