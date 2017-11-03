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

import com.founder.bj.apms.sys.service.AuthService;
import com.founder.bj.apms.sys.service.UserService;
import com.lee.jwaf.action.AbstractControllerSupport;
import com.lee.util.ObjectUtils;

/**
 * Description: 登录和令牌相关控制器.<br>
 * Created by Jimmybly Lee on 2017/6/29.
 *
 * @author Jimmybly Lee
 */
@Controller("LoginController")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class LoginController extends AbstractControllerSupport {

    /** 权限服务. */
    @Resource
    private AuthService authService;

    /** 用户服务. */
    @Resource
    private UserService userService;

    /**
     * 登录.
     */
    public void login() {
        final String userId = authService.checkAccountAndPwd(workDTO.<String>get("account"), workDTO.<String>get("pwd"));
        if (ObjectUtils.isEmpty(userId)) {
            workDTO.setResult(false);
            sessionDTO.setActiveUser(authService.getTokenByUserId("-1"));
        } else {
            workDTO.setResult(true);
            sessionDTO.setActiveUser(authService.getTokenByUserId(userId));
        }
    }

    /**
     * 退出.
     */
    public void logout() {
        sessionDTO.clearCurrentToken();
    }

    /**
     * 获得当前令牌.
     */
    @SuppressWarnings("WeakerAccess")
    public void getCurrentToken() {
        workDTO.clear();
        if (!sessionDTO.hasToken()) {
            sessionDTO.setActiveUser(authService.getTokenByUserId("-1"));
        }
        workDTO.put("user", sessionDTO.currentToken().user());
        workDTO.put("photo", userService.getUserPhoto(sessionDTO.currentToken().user().getId()));
        workDTO.put("funcs", sessionDTO.currentToken().funcs());
        workDTO.put("funcTree", sessionDTO.currentToken().funcTree());
    }

    /**
     * 保持连接.
     */
    public void keepAlive() {
        // do nothing
    }
}
