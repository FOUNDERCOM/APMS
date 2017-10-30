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

import com.founder.bj.apms.sys.entity.SysDict;
import com.founder.bj.apms.sys.entity.SysDictionary;
import com.lee.jwaf.exception.WarnException;

/**
 * Description: 字典服务.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
public interface DictService {

    /**
     * 根据ID获得实体.
     * @param id 实体ID
     * @return 实体
     */
    SysDict get(String id);

    /**
     * 根据类型和编码获得实体.
     * @param nature 类型
     * @param code 编码
     * @return 实体
     */
    SysDict getSysDictByNatureAndCode(String nature, String code);

    /**
     * 根据条件获得实体列表.
     * @param condition 条件，类型，编码，值，是否启用
     * @param start 分页开始
     * @param limit 分页长度
     * @return 实体列表
     */
    List<SysDictionary> query(SysDictionary condition, Integer start, Integer limit);

    /**
     * 根据条件获得实体数量.
     * @param condition 条件，类型，编码，值，是否启用
     * @return 实体数量
     */
    Integer count(SysDictionary condition);

    /**
     * 创建实体.
     * @param entity 游离状态实体
     * @return 持久化实体
     * @throws WarnException 有已经存在重复的类型和编码
     */
    SysDictionary create(SysDictionary entity) throws WarnException;

    /**
     * 更新实体.只更新编码，显示值，描述
     * @param entity 游离状态实体.
     * @throws WarnException 有已经存在重复的类型和编码
     */
    void update(SysDictionary entity) throws WarnException;

    /**
     * 校验类型和编码.
     * @param entity 实体
     * @return 类型和编码校验失败，有重复的，不能这样变更或添加
     */
    boolean checkNatureAndCode(SysDictionary entity);

    /**
     * 修改实体是否启用状态.
     * @param id 实体ID
     * @param isEnabled 是否启用
     */
    void changeStatus(String id, Boolean isEnabled);
}
