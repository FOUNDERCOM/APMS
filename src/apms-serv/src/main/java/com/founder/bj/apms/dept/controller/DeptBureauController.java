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

package com.founder.bj.apms.dept.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.founder.bj.apms.com.CRUDController;
import com.founder.bj.apms.com.ClientIPUtils;
import com.founder.bj.apms.dept.entity.DeptBureau;
import com.founder.bj.apms.dept.service.DeptBureauService;
import com.founder.bj.apms.sys.entity.SysUser;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.util.DateUtils;

/**
 * Description: Bureau Controller.<br>
 * Created by Jimmybly Lee on 2017/10/4.
 *
 * @author Jimmybly Lee
 */
@Controller("DeptBureauController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeptBureauController extends AbstractControllerSupport implements CRUDController {

    /**
     * Bureau Service.
     */
    @Resource
    private DeptBureauService service;

    @Override
    public void query() {
        final DeptBureau condition = workDTO.convertJsonToBeanByKey("condition", DeptBureau.class);
        final List<DeptBureau> result = service.query(condition, workDTO.getStart(), workDTO.getLimit());
        for (DeptBureau item : result) {
            item.getLastUpdateUser().setStation(null);
        }
        workDTO.setResult(result);
        workDTO.setTotle(service.count(condition));
    }

    @Override
    public void create() {
        final DeptBureau entity = workDTO.convertJsonToBeanByKey("entity", DeptBureau.class);

        entity.setIsEnabled(true);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));
        service.create(entity);
    }

    @Override
    public void update() {
        final DeptBureau entity = workDTO.convertJsonToBeanByKey("entity", DeptBureau.class);
        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));
        service.update(entity);
    }

    @Override
    public void remove() {
        final DeptBureau entity = service.get(workDTO.getInteger("id"));

        entity.setIsEnabled(false);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));

        service.update(entity);
    }

    @Override
    public void resume() {
        final DeptBureau entity = service.get(workDTO.getInteger("id"));

        entity.setIsEnabled(true);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));

        service.update(entity);
    }
}
