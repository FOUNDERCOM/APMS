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
import com.founder.bj.apms.dept.entity.DeptStation;
import com.founder.bj.apms.dept.service.DeptStationService;
import com.founder.bj.apms.sys.entity.SysUser;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.jwaf.exception.ServiceException;
import com.lee.util.DateUtils;

/**
 * Description: Dept Station Controller.<br>
 * Created by Jimmybly Lee on 2017/10/4.
 *
 * @author Jimmybly Lee
 */
@Controller("DeptStationController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeptStationController extends AbstractControllerSupport implements CRUDController {

    /**
     * Bureau Service.
     */
    @Resource
    private DeptStationService service;

    @Override
    public void query() {
        final DeptStation condition = workDTO.convertJsonToBeanByKey("condition", DeptStation.class);
        final List<DeptStation> result = service.query(condition, workDTO.getStart(), workDTO.getLimit());
        for (DeptStation item : result) {
            item.getLastUpdateUser().setStation(null);
        }
        workDTO.setResult(result);
        workDTO.setTotle(service.count(condition));
    }

    @Override
    public void create() throws ServiceException {
        final DeptStation entity = workDTO.convertJsonToBeanByKey("entity", DeptStation.class);

        entity.setIsEnabled(true);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));

        service.create(entity);
    }

    @Override
    public void update() throws ServiceException {
        final DeptStation entity = workDTO.convertJsonToBeanByKey("entity", DeptStation.class);
        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));

        service.update(entity);
    }

    @Override
    public void remove() {
        final DeptStation entity = service.get(workDTO.<String>get("id"));

        entity.setIsEnabled(false);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));

        try {
            service.update(entity);
        } catch (ServiceException ex) {
            log.error("this should never happen");
        }
    }

    @Override
    public void resume() {
        final DeptStation entity = service.get(workDTO.<String>get("id"));

        entity.setIsEnabled(true);

        entity.setLastUpdateUser(new SysUser());
        entity.getLastUpdateUser().setId(sessionDTO.currentToken().user().getId());
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
        entity.setLastUpdateIp(ClientIPUtils.getClientIp(servletRequest));

        try {
            service.update(entity);
        } catch (ServiceException ex) {
            log.error("this should never happen");
        }
    }
}
