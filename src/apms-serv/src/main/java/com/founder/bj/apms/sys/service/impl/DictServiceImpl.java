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

package com.founder.bj.apms.sys.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.founder.bj.apms.sys.entity.SysDict;
import com.founder.bj.apms.sys.entity.SysDictionary;
import com.founder.bj.apms.sys.service.DictService;
import com.lee.jwaf.exception.WarnException;
import com.lee.util.ObjectUtils;
import com.lee.util.StringUtils;

// CSOFF: RegexpSinglelineJava
/**
 * Description: 字典服务.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class DictServiceImpl implements DictService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    @Transactional(readOnly = true)
    public SysDict get(String id) {
        return em.find(SysDict.class, id);
    }

    @Override
    public SysDict getSysDictByNatureAndCode(String nature, String code) {
        return (SysDict) em.createQuery("from SysDict where nature = :nature and code = :code")
            .setParameter("nature", nature).setParameter("code", code).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysDictionary> query(SysDictionary condition, Integer start, Integer limit) {
        String hql = " from SysDictionary as d";
        hql += " left join fetch d.parent";
        hql += " where d.isEnabled = :isEnabled";

        if (!StringUtils.isEmpty(condition.getNature())) {
            hql += " and d.nature like :dictNature";
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            hql += " and d.code like :dictCode";
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            hql += " and d.value like :dictValue";
        }
        if (!ObjectUtils.isEmpty(condition.getIsNature())) {
            hql += " and d.isNature = :isNature";
        }

        hql += " order by d.code";

        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit);

        query.setParameter("isEnabled", condition.getIsEnabled());

        if (!StringUtils.isEmpty(condition.getNature())) {
            query.setParameter("dictNature", "%" + condition.getNature().toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            query.setParameter("dictCode", "%" + condition.getCode().toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            query.setParameter("dictValue", "%" + condition.getValue() + "%");
        }
        if (!ObjectUtils.isEmpty(condition.getIsNature())) {
            query.setParameter("isNature", condition.getIsNature());
        }

        //noinspection unchecked
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer count(SysDictionary condition) {
        String hql = " select count(d) from SysDictionary as d";
        hql += " where d.isEnabled = :isEnabled";

        if (!StringUtils.isEmpty(condition.getNature())) {
            hql += " and d.nature like :dictNature";
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            hql += " and d.code like :dictCode";
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            hql += " and d.value like :dictValue";
        }
        if (!ObjectUtils.isEmpty(condition.getIsNature())) {
            hql += " and d.isNature = :isNature";
        }

        final Query query = em.createQuery(hql);

        query.setParameter("isEnabled", condition.getIsEnabled());

        if (!StringUtils.isEmpty(condition.getNature())) {
            query.setParameter("dictNature", "%" + condition.getNature().toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(condition.getCode())) {
            query.setParameter("dictCode", "%" + condition.getCode().toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(condition.getValue())) {
            query.setParameter("dictValue", "%" + condition.getValue() + "%");
        }
        if (!ObjectUtils.isEmpty(condition.getIsNature())) {
            query.setParameter("isNature", condition.getIsNature());
        }

        return ((Number) query.getSingleResult()).intValue();
    }

    @Override
    public SysDictionary create(SysDictionary entity) throws WarnException {
        if (checkNatureAndCode(entity)) {
            throw new WarnException("有已经存在重复的类型和编码！");
        }
        entity.setParent(em.find(SysDictionary.class, entity.getParent().getId()));
        entity.setIsEnabled(true);
        em.persist(entity);
        return entity;
    }

    @Override
    public void update(SysDictionary entity) throws WarnException {
        if (checkNatureAndCode(entity)) {
            throw new WarnException("有已经存在重复的类型和编码！");
        }
        final SysDictionary entityInDB = em.find(SysDictionary.class, entity.getId());
        entityInDB.setCode(entity.getCode());
        entityInDB.setValue(entity.getValue());
        entityInDB.setDesc(entity.getDesc());
    }

    @Override
    @SuppressWarnings("WeakerAccess")
    @Transactional(readOnly = true)
    public boolean checkNatureAndCode(SysDictionary entity) {
        String hql = " select count(d) from SysDictionary as d";
        hql += " where d.nature = :nature and d.code = :code";
        if (!ObjectUtils.isEmpty(entity)) {
            hql += " and d.id = :id";
        }
        final Query query = em.createQuery(hql);
        query.setParameter("nature", entity.getNature());
        query.setParameter("code", entity.getCode());
        if (!ObjectUtils.isEmpty(entity)) {
            query.setParameter("id", entity.getId());
        }
        return ((Number) query.getSingleResult()).intValue() > 0;
    }

    @Override
    public void changeStatus(String id, Boolean isEnabled) {
        em.find(SysDictionary.class, id).setIsEnabled(isEnabled);
    }

}
