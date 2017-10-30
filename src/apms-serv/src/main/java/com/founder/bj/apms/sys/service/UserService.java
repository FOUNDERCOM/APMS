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

package com.founder.bj.apms.sys.service;

import java.util.List;

import com.founder.bj.apms.sys.entity.SysUser;
import com.founder.bj.apms.sys.entity.SysUserAccount;
import com.founder.bj.apms.sys.entity.SysUserPhoto;
import com.lee.jwaf.exception.WarnException;

// CSOFF: RegexpSinglelineJava

/**
 * Description: 用户服务.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
public interface UserService {

    /**
     * 根据ID获得实体.
     *
     * @param id 实体ID
     * @return 实体
     */
    SysUser get(Integer id);

    /**
     * 根据ID获得用户，并把照片一并返回.
     *
     * @param id 用户ID
     * @return 用户实体，拥有照片
     */
    SysUserPhoto getUserPhoto(String id);

    /**
     * 根据id获得用户帐号.
     *
     * @param id 用户id
     * @return 用户帐号
     */
    SysUserAccount getUserAccount(String id);

    /**
     * 根据条件返回实体列表.
     *
     * @param condition 可能参数，包括用户名，是否启用，处室名，处室ID
     * @param start     分页开始
     * @param limit     分页长度
     * @return 实体列表
     */
    List<SysUser> query(SysUser condition, Integer start, Integer limit);

    /**
     * 根据条件返回用户个数.
     *
     * @param condition 可能参数，包括用户名，处室名，处室ID
     * @return 用户个数
     */
    int count(SysUser condition);

    /**
     * 创建用户.
     *
     * @param entity  用户游离实体
     * @return 持久实体
     * @throws WarnException 账号没校验通过
     */
    SysUser create(SysUser entity) throws WarnException;

    /**
     * 更新用户实体.只更新用户名、电话、邮箱、头像
     *
     * @param entity  实体
     * @throws WarnException 账号重复
     */
    void update(SysUser entity) throws WarnException;

    /**
     * 校验用户帐号.
     *
     * @param user 用户实体，校验其中的用户id以及账号
     * @return true for 校验失败，已经有重复账号的用户了.
     */
    boolean checkUserAccount(SysUserAccount user);

    /**
     * 更新用户密码.
     *
     * @param entity 用户实体
     * @throws WarnException 账号没校验通过
     */
    void updateAccount(SysUserAccount entity) throws WarnException;

    /**
     * 更新头像.
     * @param photo 头像实体
     */
    void updatePhoto(SysUserPhoto photo);

    /**
     * 修改实体是否启用状态.
     *
     * @param id        实体ID
     * @param isEnabled 是否启用
     */
    void changeStatus(Integer id, Boolean isEnabled);
}
