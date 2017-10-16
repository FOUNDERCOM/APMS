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

package com.founder.bj.apms.com;

import com.lee.jwaf.exception.ServiceException;

/**
 * Description: Common CRUD Controller.<br>
 * Created by Jimmybly Lee on 2017/10/4.
 *
 * @author Jimmybly Lee
 */
public interface CRUDController {

    /**
     * Query Entities.
     */
    void query();

    /**
     * Create entity.
     *
     * @throws ServiceException validation fall
     */
    void create() throws ServiceException;

    /**
     * Update entity.
     *
     * @throws ServiceException validation fall
     */
    void update() throws ServiceException;

    /**
     * Remove entity.
     */
    void remove();

    /**
     * Resume entity.
     */
    void resume();

}
