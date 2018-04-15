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
     *
     * @param id id
     * @return {@link AuxInfo} entity
     */
    AuxInfo get(String id);

    /**
     * Query by condition.
     *
     * @param condition condition dto
     * @return list of entities
     */
    List<AuxInfo> queryAll(AuxInfo condition);

    /**
     * Query by condition.
     *
     * @param condition condition dto
     * @param start     page start.
     * @param limit     page limit, if it's greater than 0
     * @return list of entities
     */
    List<AuxInfo> query(AuxInfo condition, Integer start, Integer limit);

    /**
     * Count by condition.
     *
     * @param condition condition dto
     * @return sum of the entities
     */
    Integer count(AuxInfo condition);

    /**
     * Persist the Entity.
     *
     * @param entity the Entity
     * @return the id of the persisted entity's id
     * @throws ServiceException validation fall
     */
    String create(AuxInfo entity) throws ServiceException;

    /**
     * Merge the Entity.
     *
     * @param entity the entity
     * @throws ServiceException validation fall
     */
    void update(AuxInfo entity) throws ServiceException;

    /**
     * Remove the Stuff info of the {@link AuxInfo}.
     *
     * @param id   the stuff info id
     * @param type the type of the stuff class
     */
    void removeStuff(String id, Class<? extends AuxStuff> type);

    /**
     * Set Status.
     *
     * @param token     token
     * @param ip        client ip
     * @param id        aux id
     * @param isEnabled isEnabled
     */
    void setStatus(Token token, String ip, String id, Boolean isEnabled);

    /**
     * Change Salary.
     *
     * @param token token
     * @param ip    client ip
     * @param id    aux id
     * @param base  基本工资
     * @param bonus 奖金
     * @param tax   所得税
     * @param sss   个付社保
     * @param sFund 个付公积金
     * @param css   司付社保
     * @param cFund 司付公积金
     */
    void changeSalary(Token token, String ip, String id, Integer base, Integer bonus, Integer tax, Integer sss, Integer sFund, Integer css, Integer cFund);

    /**
     * Check Duplicated Identity Card.
     *
     * @param id   {@link AuxInfo} id, 0 for null
     * @param card card
     * @return true for duplicated
     */
    boolean checkDuplicatedIdentityCare(String id, String card);

}
