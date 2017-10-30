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

import com.founder.bj.apms.sys.entity.SysDictionary;
import com.founder.bj.apms.sys.service.DictService;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.jwaf.exception.WarnException;

/**
 * Description: 字典管理.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 *
 * @author Jimmybly Lee
 */
@Controller("DictController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class DictController extends AbstractControllerSupport {

    /** 字典服务. */
    @Resource
    private DictService service;

    /**
     * 查询实体.
     */
    public void query() {
        final SysDictionary condition = workDTO.convertJsonToBeanByKey("condition", SysDictionary.class);
        workDTO.setResult(service.query(condition, workDTO.getStart(), workDTO.getLimit()));
        workDTO.setTotle(service.count(condition));
    }

    /**
     * 创建实体.
     * @throws WarnException 有已经存在重复的类型和编码
     */
    public void create() throws WarnException {
        service.create(workDTO.convertJsonToBeanByKey("entity", SysDictionary.class));
    }

    /**
     * 修改实体.
     * @throws WarnException 有已经存在重复的类型和编码
     */
    public void update() throws WarnException {
        service.update(workDTO.convertJsonToBeanByKey("entity", SysDictionary.class));
    }

    /**
     * 删除实体.
     */
    public void remove() {
        service.changeStatus(workDTO.<String>get("id"), false);
    }

    /**
     * 恢复删除实体.
     */
    public void resume() {
        service.changeStatus(workDTO.<String>get("id"), true);
    }
}
