package com.interview.hibernate.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.interview.hibernate.dao.SearchDao;

/**
 *
 * @author Mujoko
 *
 * @param <T>
 * @param <ID>
 */
//@Transactional(readOnly=true)
public class SearchDaoImpl<T, ID extends Serializable> extends
        GenericDaoImpl<T, Serializable> implements SearchDao<T, Serializable> {
    /**
     *  construct log object to debug.
     */
    protected static final Logger LOG = Logger.getLogger(SearchDaoImpl.class);

    /**
     *
     * @return {@link List}
     */

    public List<T> find() {
        return find(null, -1, -1, null, null);
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

    public final List<T> find(final int start, final int limit) {
        return find(null, start, limit, null, null);
    }

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
     *            directions asc/desc.
     * @return {@link List}
     */

    public final List<T> find(
            final int start, final int limit, final String sort,
            final String dir) {
        return find(null, start, limit, sort, dir);
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
    @SuppressWarnings({  "unchecked" })
    @Transactional(readOnly=true)
    public List<T> find(final String parameters,
            final int start, final int limit,
            final String sort, final String dir) {
        List<T> finalResult = null;
        
        
        try {
            StringBuffer queryString = new StringBuffer(" from "
            		+ getDomainClass().getSimpleName() + " a where (a.deleted = false or  a.deleted is null )  ");
            if (parameters != null && StringUtils.isNotEmpty(parameters)) {
                queryString.append(" and ( ").append(parameters).append(" ) ");
            }
            if (sort != null && dir != null) {
                queryString.append(" order by a.").append(sort).append(" ")
                        .append(dir);
            }
            Session session = getSessionFactory().getCurrentSession();
            Query query = session.createQuery(queryString.toString());
            if (start >= 0) {
                query.setFirstResult(start);
            }
            if (limit >= 0) {
                query.setMaxResults(limit);
            }
            finalResult = query.list();

            logger.debug(queryString);
        } catch (HibernateException e) {
            LOG.error(e.getMessage());
        }
        
        
        if (finalResult == null) {
            finalResult = new ArrayList<T>();
        }
        return finalResult;
    }

    /**
     * count method.
     *
     * @param params
     *            parameter query.
     * @return long
     */
    @SuppressWarnings("unchecked")
    public final Long count(final String params) {
        StringBuffer query = new StringBuffer("select count(a.id) from "
                + getDomainClass().getName() + " a  where (a.deleted = false or  a.deleted is null )   ");


        if (StringUtils.isNotEmpty(params)) {
            query.append(" and ( ").append(params).append(" )");
        }

        List<T> list = getHibernateTemplate().find(query.toString());
        Long count = (Long) list.get(0);

        return count;
    }

    /**
     * count method.
     *
     * @param params
     *            parameter query.
     * @param id
     *            parameter id.
     * @return long
     */
    @SuppressWarnings("unchecked")
    public final Long duplicateCheck(final Map<String, Object> params,
            final String id) {
        StringBuffer query = new StringBuffer("select count(a.id) from "
                + getDomainClass().getName() + " a  where (a.deleted = false or  a.deleted is null )   ");

        if (id != null) {
            query.append(" and a.id <> '").append(id).append("' ");
        }

        String parameters = getParams(params, false);
        query.append(" and ( ").append(parameters).append(" )");

        List<T> list = getHibernateTemplate().find(query.toString());
        Long count = (Long) list.get(0);

        return count;
    }

    /**
     * findByQueryLimit method.
     *
     * @param whereClause
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
    @SuppressWarnings({  "unchecked" })
    public final List<T> findByQueryLimit(final String whereClause,
            final int start, final int limit, final String sort,
            final String dir) {
        List<T> finalResult = null;
        try {

            StringBuffer queryString = new StringBuffer("from "
                    + getDomainClass().getName()
                    + " a where (a.deleted = false or a.deleted is null) ");
            if (whereClause != null && StringUtils.isNotEmpty(whereClause)) {
                queryString.append(" and ( ").append(whereClause).append(" ) ");
            }
            if (sort != null) {
                queryString.append(" order by a.").append(sort).append(" ")
                        .append(dir);
            }
            LOG.debug(queryString);
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            Query query = session.createQuery(queryString.toString());
            if (start >= 0) {
                query.setFirstResult(start);
            }
            if (limit > 0) {
                query.setMaxResults(limit);
            }
            finalResult = query.list();
        } catch (HibernateException e) {
            LOG.error(e.getMessage());
        }
        if (finalResult == null) {
            finalResult = new ArrayList<T>();
        }

        return finalResult;
    }


    /**
     * getParams. DO NOT CHANGE TO FINAL MODIFIER.
     *
     * @param params
     *            map object:value
     * @param useOrInParam
     *            Use or
     * @return parameterQuery
     */
    @SuppressWarnings("rawtypes")
    public String getParams(final Map<String, Object> params,
            final boolean useOrInParam) {
        String parameterQuery = null;
        if (params != null && params.size() > 0) {
            String keySeparator = null;
            StringBuffer parameter = new StringBuffer();
            Iterator keyValuePairs1 = params.entrySet().iterator();
            for (int i = 0; i < params.size(); i++) {
                if (i == 0) {
                    keySeparator = "";
                } else if (i > 0 && useOrInParam) {
                    keySeparator = " or ";
                } else {
                    keySeparator = " and ";
                }

                Map.Entry entry = (Map.Entry) keyValuePairs1.next();
                String key = (String) entry.getKey();
                Object value = params.get(key);
                if (value instanceof String || value instanceof Integer
                        || value instanceof Long) {
                    setParamString(parameter, key, value, keySeparator);
                } else {
                    parameter.append(keySeparator).append(" a").append(".")
                            .append(key).append(" = ").append("'")
                            .append(value).append("'");
                }
            }
            parameterQuery = parameter.toString().trim();
        } else {
            parameterQuery = "";
        }
        return parameterQuery;
    }

    /**
     * @param parameter
     *            query builder .
     * @param key
     *            field .
     * @param value
     *            value .
     * @param keySeparator
     *            .
     */
    private void setParamString(final StringBuffer parameter, final String key,
            final Object value, final String keySeparator) {
        parameter.append(keySeparator).append(" a").append(".").append(key)
                .append(" = ").append("'").append(value).append("'");
    }

}
