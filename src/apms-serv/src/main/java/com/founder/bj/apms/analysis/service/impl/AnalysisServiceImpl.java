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

package com.founder.bj.apms.analysis.service.impl;

import com.founder.bj.apms.analysis.service.AnalysisService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Description: TODO.<br>
 * Created by Jimmybly Lee on 2017/10/10.
 *
 * @author Jimmybly Lee
 */
@Transactional(readOnly = true)
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnalysisServiceImpl implements AnalysisService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    public Integer getAuxNum() {
        String sql = "SELECT count(1) from APMS_AUX_INFO where IS_ENABLED = '1'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }

    @Override
    public Integer getDegreeNum() {
        String sql = "SELECT count(1) from APMS_AUX_INFO where IS_ENABLED = '1' AND AUX_EDUCATION_DEGREE > '1'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }

    @Override
    public Integer getAvgSalary() {
        String sql = "SELECT AVG(BUREAU_STD_SALARY) from APMS_DEPT_BUREAU where IS_ENABLED = '1'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }

    @Override
    public Integer getGoodPern() {
        String sql = "SELECT COUNT(1) from APMS_AUX_INFO where IS_ENABLED = '1' AND AUX_POLITICAL_STATUS = '1'";
        return ((Number) em.createNativeQuery(sql).getSingleResult()).intValue();
    }
}
