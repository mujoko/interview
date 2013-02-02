/**
 * Copyright (c) {2011} {meter@rbtsb.com} {
 * individual contributors as indicated by the @authors tag}.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Private License v1.0
 * which accompanies this distribution, and is available at
 * http://www.rbtsb.com
 */
package com.interview.hibernate.dao;

import java.io.Serializable;

import com.interview.exception.DatabaseException;

/**
 * Generic Data Access Object (Dao) interface. This is an interface used to
 * provide generic methods to all Daos.
 *
 * @author Mujoko
 *
 * @param <T>
 * @param <ID>
 */
public interface GenericDao<T, ID extends Serializable> {

    /** Rawtypes constant. */
    String RAWTYPES = "rawtypes";

    /**
     * @return long
     */
    Long count()throws DatabaseException;


    /**
     * @param domain
     *            domain model
     */
    void delete(final T domain)throws DatabaseException;

    /**
     * Get.
     *
     * @param id
     *            to search for
     * @return Type if found
     */
    T get(final ID id) throws DatabaseException;


    /**
     * deleteAll implements multiple records deletion physically.
     *
     * @param ids
     *            array of primary keys
     */
    void deleteAll(final String[] ids)throws DatabaseException;


    /**
     * @param domain
     *            domain model
     */
    void save(final T domain) throws DatabaseException;

    /**
     * @param domain
     *            domain model
     */
    void saveOrUpdate(final T domain) throws DatabaseException;

    /**
     * @param domain
     *            domain model
     */
    void update(final T domain)throws DatabaseException;
}
