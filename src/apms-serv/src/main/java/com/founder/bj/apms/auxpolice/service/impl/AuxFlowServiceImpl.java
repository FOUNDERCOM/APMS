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

package com.founder.bj.apms.auxpolice.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.founder.bj.apms.auxpolice.entity.AuxInfo;
import com.founder.bj.apms.auxpolice.service.AuxFlowService;
import com.founder.bj.apms.auxpolice.service.AuxInfoService;
import com.founder.bj.apms.sys.entity.SysUser;
import com.founder.bj.apms.sys.service.DictService;
import com.lee.jwaf.token.Token;
import com.lee.util.DateUtils;

/**
 * Description: impl for {@link AuxFlowService}.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuxFlowServiceImpl implements AuxFlowService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    /** Info Service. */
    @Resource
    private AuxInfoService infoService;

    /** Dict Service. */
    @Resource
    private DictService dictService;

    @Override
    public void apply(Token token, String ip, String id) {
        final AuxInfo entity = infoService.get(id);
        entity.setStatus(dictService.getSysDictByNatureAndCode("PROCESS_STATUS", "TO_ACCEPT"));
        entity.setLastUpdateUser(em.find(SysUser.class, token.user().getId()));
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ip);

    }

    @Override
    public void accept(Token token, String ip, String id) {
        final AuxInfo entity = infoService.get(id);
        entity.setStatus(dictService.getSysDictByNatureAndCode("PROCESS_STATUS", "TO_APPROVE"));
        entity.setLastApproveUser(em.find(SysUser.class, token.user().getId()));
        entity.setLastApproveDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastApproveIp(ip);
    }

    @Override
    public void pass(Token token, String ip, String id) {
        final AuxInfo entity = infoService.get(id);
        entity.setStatus(dictService.getSysDictByNatureAndCode("PROCESS_STATUS", "PASSED"));
        entity.setLastApproveUser(em.find(SysUser.class, token.user().getId()));
        entity.setLastApproveDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastApproveIp(ip);
    }

    @Override
    public void reject(Token token, String ip, String id) {
        final AuxInfo entity = infoService.get(id);
        entity.setStatus(dictService.getSysDictByNatureAndCode("PROCESS_STATUS", "REJECTED"));
        entity.setLastApproveUser(em.find(SysUser.class, token.user().getId()));
        entity.setLastApproveDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastApproveIp(ip);
    }
}
