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

package com.founder.bj.apms.att.service.impl;

import com.founder.bj.apms.sys.entity.SysUser;
import com.lee.util.ObjectUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.founder.bj.apms.att.entity.Attachment;
import com.founder.bj.apms.att.service.AttService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Description: 附件服务实现类.<br>
 * Created by Jimmybly Lee on 2017/10/18.
 *
 * @author Jimmybly Lee
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AttServiceImpl implements AttService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    @Transactional
    public String create(Attachment att) {
        if (!ObjectUtils.isEmpty(att.getLastUpdateUser()) && !ObjectUtils.isEmpty(att.getLastUpdateUser().getId())) {
            att.setLastUpdateUser(em.find(SysUser.class, att.getLastUpdateUser().getId()));
        }
        em.persist(att);
        return att.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Attachment get(String id) {
        return em.find(Attachment.class, id);
    }
}
