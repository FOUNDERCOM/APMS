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

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.founder.bj.apms.sys.entity.SysFunc;
import com.founder.bj.apms.sys.service.AuthService;
import com.lee.jwaf.action.AbstractControllerSupport;

/**
 * Description: 权限控制器.<br>
 * Created by Jimmybly Lee on 2017/7/9.
 *
 * @author Jimmybly Lee
 */
@Controller("AuthController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class AuthController extends AbstractControllerSupport {

    /** 权限服务.*/
    @Resource
    private AuthService service;

    /**
     * 获取用户权限列表.
     */
    public void queryAuth() {
        final List<SysFunc> result = service.queryAllFunc();
        final List<String> ids = service.queryFuncIdByUser(workDTO.<String>get("userId"));
        for (SysFunc func : result) {
            for (String id : ids) {
                if (func.getId().equals(id)) {
                    func.setIsAssigned(true);
                    break;
                }
            }
        }
        workDTO.setResult(result);
    }

    /**
     * 为每一个叶子节点设置是否已经被用户配置过相应权限.
     * @param node 当前节点
     * @param ids 用户拥有的权限id列表
     */
    private void setStatus(SysFunc node, List<String> ids) {
        node.setParent(null);
        if (!node.getIsLeaf()) {
            for (SysFunc child : node.getChildren()) {
                setStatus(child, ids);
            }
        } else {
            for (String id : ids) {
                if (node.getId().equals(id)) {
                    node.setIsAssigned(true);
                }
            }
        }
    }

    /**
     * 为用户指定权限.
     */
    public void assignAuth() {
        service.assignFuncToUser(workDTO.<String>get("userId"), workDTO.<String>get("funcId"),
            "true".equals(workDTO.get("assign")));
    }

    /**
     * 获得所有菜单的有序列表.
     */
    public void queryFuncList() {
        workDTO.setResult(service.queryAllFunc());
    }
}
