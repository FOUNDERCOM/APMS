/* ***************************************************************************
 * EZ.JWAF/EZ.JCWAP: Easy series Production.
 * Including JWAF(Java-based Web Application Framework)
 * and JCWAP(Java-based Customized Web Application Platform).
 * Copyright (C) 2016-2017 the original author or authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of MIT License as published by
 * the Free Software Foundation;
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the MIT License for more details.
 *
 * You should have received a copy of the MIT License along
 * with this library; if not, write to the Free Software Foundation.
 * ***************************************************************************/

package com.founder.bj.apms.dept.service;

import java.io.Serializable;
import java.util.List;

import com.founder.bj.apms.dept.entity.DeptStation;
import com.lee.jwaf.exception.ServiceException;

/**
 * Description: Dept Service Interface.<br>
 * Created by Jimmybly Lee on 2017/10/3.
 *
 * @author Jimmybly Lee
 */
public interface DeptStationService extends Serializable {

    /**
     * Get Persisted entity.
     * @param id entity id
     * @return entity
     */
    DeptStation get(String id);

    /**
     * Query entity list.
     * @param condition condition
     * @param start page start
     * @param limit page limit
     * @return list of entity
     */
    List<DeptStation> query(DeptStation condition, Integer start, Integer limit);

    /**
     * Count entity by condition.
     * @param condition condition
     * @return count num
     */
    Integer count(DeptStation condition);

    /**
     * Create entity.
     * @param entity entity.
     * @return entity persisted id
     *
     * @throws ServiceException validation fall
     */
    String create(DeptStation entity) throws ServiceException;

    /**
     * Update entity.
     * @param entity entity
     *
     * @throws ServiceException validation fall
     */
    void update(DeptStation entity) throws ServiceException;
}
