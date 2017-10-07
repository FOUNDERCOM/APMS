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

package com.founder.bj.apms.sys.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.founder.bj.apms.sys.entity.SysUser;
import com.founder.bj.apms.sys.entity.SysUserAccount;
import com.founder.bj.apms.sys.entity.SysUserPhoto;
import com.founder.bj.apms.sys.service.UserService;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.jwaf.exception.WarnException;

import java.util.List;

/**
 * Description: 用户管理.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 *
 * @author Jimmybly Lee
 */
@Controller("UserController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class UserController extends AbstractControllerSupport {

    /** 用户服务. */
    @Resource
    private UserService service;

    /**
     * 查询实体.
     */
    public void query() {
        final SysUser condition = workDTO.convertJsonToBeanByKey("condition", SysUser.class);
        final List<SysUser> result = service.query(condition, workDTO.getStart(), workDTO.getLimit());
        for (SysUser item : result) {
            item.getStation().setLastUpdateUser(null);
            item.getStation().getBureau().setLastUpdateUser(null);
        }
        workDTO.setResult(result);
        workDTO.setTotle(service.count(condition));
    }

    /**
     * 创建实体.
     * @throws WarnException 有重复的账号，不能创建
     */
    public void create() throws WarnException {
        service.create(workDTO.convertJsonToBeanByKey("entity", SysUser.class));
    }

    /**
     * 修改实体.
     * @throws WarnException 有重复的账号，不能修改
     */
    public void update() throws WarnException {
        service.update(workDTO.convertJsonToBeanByKey("entity", SysUser.class));
    }

    /**
     * 更新账户.
     * @throws WarnException 有重复的账号，不能修改
     */
    public void updateAccount() throws WarnException {
        service.updateAccount(workDTO.convertJsonToBeanByKey("entity", SysUserAccount.class));
    }

    /**
     * 更新头像.
     */
    public void updatePhoto() {
        service.updatePhoto(workDTO.convertJsonToBeanByKey("entity", SysUserPhoto.class));
    }

    /**
     * 删除实体.
     */
    public void remove() {
        service.changeStatus(workDTO.getInteger("id"), false);
    }

    /**
     * 恢复删除实体.
     */
    public void resume() {
        service.changeStatus(workDTO.getInteger("id"), true);
    }

    /**
     * 修改密码.
     * @throws WarnException 有重复的账号，不能修改
     */
    public void changePassword() throws WarnException {
        service.updateAccount(workDTO.convertJsonToBeanByKey("entity", SysUserAccount.class));
    }
}
