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
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author reliable
 *
 * @param <T>
 * @param <ID>
 */
public interface SearchDao<T, ID extends Serializable> extends
        GenericDao<T, Serializable> {

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
            final String sort, final String dir);

    /**
     * find method.
     *
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
    List<T> find(final int start, final int limit,
            final String sort, final String dir);

    /**
     * find method.
     * @return list
     */
    @Transactional(readOnly = true)
    List<T> find();

    /**
     * find method.
     *
     * @param start
     *            start record
     * @param limit
     *            limit record to be shown
     * @return list
     */
    @Transactional(readOnly = true)
    List<T> find(final int start, final int limit);

    /**
     * count method.
     *
     * @param params
     *            parameter query.
     * @return long
     */
    @Transactional(readOnly = true)
    Long count(String params);

    /**
     *
     * @param whereClause
     * Filter parameters.
     * @param start
     * Start Selection index.
     * @param limit
     * Size of selection.
     * @param sort
     * Sorting by.
     * @param dir
     * Ascending or descending.
     * @return List.
     */
    List<T> findByQueryLimit(final String whereClause,
            final int start, final int limit,
            final String sort, final String dir);

    /**
     * count method.
     *
     * @param params
     *            parameter query.
     * @param id
     *            parameter id.
     * @return long
     */
    @Transactional(readOnly = true)
    Long duplicateCheck(final Map<String, Object> params,
            final String id);
}
