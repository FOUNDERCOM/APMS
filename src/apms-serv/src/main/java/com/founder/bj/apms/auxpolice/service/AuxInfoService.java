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

package com.founder.bj.apms.auxpolice.service;

import java.util.List;

import com.founder.bj.apms.auxpolice.entity.AuxInfo;
import com.founder.bj.apms.auxpolice.entity.AuxStuff;
import com.lee.jwaf.exception.ServiceException;
import com.lee.jwaf.token.Token;

/**
 * Description: Aux Information service.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
public interface AuxInfoService {

    /**
     * Get {@link AuxInfo} by id.
     * @param id id
     * @return {@link AuxInfo} entity
     */
    AuxInfo get(Integer id);

    /**
     * Query by condition.
     * @param condition condition dto
     * @param start page start.
     * @param limit page limit, if it's greater than 0
     * @return list of entities
     */
    List<AuxInfo> query(AuxInfo condition, Integer start, Integer limit);

    /**
     * Count by condition.
     * @param condition condition dto
     * @return sum of the entities
     */
    Integer count(AuxInfo condition);

    /**
     * Persist the Entity.
     * @param entity the Entity
     * @return the id of the persisted entity's id
     *
     * @throws ServiceException validation fall
     */
    Integer create(AuxInfo entity) throws ServiceException;

    /**
     * Merge the Entity.
     * @param entity the entity
     *
     * @throws ServiceException validation fall
     */
    void update(AuxInfo entity) throws ServiceException;

    /**
     * Remove the Stuff info of the {@link AuxInfo}.
     * @param id the stuff info id
     * @param type the type of the stuff class
     */
    void removeStuff(Integer id, Class<? extends AuxStuff> type);

    /**
     * Set Status.
     * @param token token
     * @param ip client ip
     * @param id aux id
     * @param isEnabled isEnabled
     */
    void setStatus(Token token, String ip, Integer id, Boolean isEnabled);

    /**
     * Change Salary.
     * @param token token
     * @param ip client ip
     * @param id aux id
     * @param salary Salary
     */
    void changeSalary(Token token, String ip, Integer id, Integer salary);

}
