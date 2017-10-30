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

package com.founder.bj.apms.dept.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.founder.bj.apms.dept.entity.DeptStation;
import com.founder.bj.apms.dept.service.DeptStationService;
import com.founder.bj.apms.sys.entity.SysUser;
import com.lee.jwaf.exception.ServiceException;
import com.lee.util.Assert;
import com.lee.util.ObjectUtils;
import com.lee.util.StringUtils;

// CSOFF: RegexpSinglelineJava
// CSOFF: ParameterName
/**
 * Description: impl for {@link DeptStationService}.<br>
 * Created by Jimmybly Lee on 2017/10/3.
 *
 * @author Jimmybly Lee
 */
@Transactional(readOnly = true)
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeptStationServiceImpl implements DeptStationService {

    private static final long serialVersionUID = 9027996760595333266L;

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    public DeptStation get(String id) {
        Assert.notNull(id);

        return em.find(DeptStation.class, id);
    }

    @Override
    public List<DeptStation> query(DeptStation condition, Integer start, Integer limit) {
        Assert.notNull(condition);
        Assert.notNull(condition.getIsEnabled());

        String hql = "from DeptStation s";
        hql += " left join fetch s.bureau";
        hql += " left join fetch s.lastUpdateUser";
        hql += " where s.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and s.name like :name";
        }
        if (!ObjectUtils.isEmpty(condition.getBureau()) && !ObjectUtils.isEmpty(condition.getBureau().getId())) {
            hql += " and s.bureau.id = :bureauId";
        }
        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit < 0 ? Integer.MAX_VALUE : limit);
        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("name", "%" + condition.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(condition.getBureau()) && !ObjectUtils.isEmpty(condition.getBureau().getId())) {
            query.setParameter("bureauId", condition.getBureau().getId());
        }
        //noinspection unchecked
        return query.getResultList();
    }

    @Override
    public Integer count(DeptStation condition) {
        Assert.notNull(condition);
        Assert.notNull(condition.getIsEnabled());

        String hql = "select count(s) from DeptStation s";
        hql += " where s.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and s.name like :name";
        }
        if (!ObjectUtils.isEmpty(condition.getBureau()) && !ObjectUtils.isEmpty(condition.getBureau().getId())) {
            hql += " and s.bureau.id = :bureauId";
        }
        final Query query = em.createQuery(hql);
        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("name", "%" + condition.getName() + "%");
        }
        if (!ObjectUtils.isEmpty(condition.getBureau()) && !ObjectUtils.isEmpty(condition.getBureau().getId())) {
            query.setParameter("bureauId", condition.getBureau().getId());
        }
        //noinspection unchecked
        return ((Number) query.getSingleResult()).intValue();
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    @Override
    public String create(DeptStation entity) throws ServiceException {
        entity.setId(null);
        validate(entity);
        entity.setLastUpdateUser(em.find(SysUser.class, entity.getLastUpdateUser().getId()));

        em.persist(entity);
        return entity.getId();
    }

    /**
     * {@link DeptStation} Entity validator.<br>
     *     validate not null
     *     <ul>
     *         <li>name</li>
     *         <li>strength</li>
     *         <li>contact name</li>
     *         <li>contact tel</li>
     *         <li>contact mobile</li>
     *         <li>last update user</li>
     *         <li>last update ip</li>
     *         <li>last update date</li>
     *         <li>isEnabled</li>
     *     </ul>
     *
     * @param entity target entity
     *
     * @throws ServiceException validation fall
     */
    private void validate(DeptStation entity) throws ServiceException {
        try {
            Assert.hasLength(entity.getName(), "名称不能为空。");
            Assert.notNull(entity.getStrength(), "编制不能为空。");

            Assert.hasLength(entity.getContactName(), "联系人姓名不能为空。");
            Assert.hasLength(entity.getContactTel(), "联系人座机不能为空。");
            Assert.hasLength(entity.getContactMobile(), "联系人手机不能为空");

            Assert.notNull(entity.getLastUpdateUser());
            Assert.notNull(entity.getLastUpdateUser().getId());
            final SysUser updater = em.find(SysUser.class, entity.getLastUpdateUser().getId());
            Assert.notNull(updater.getName());
            Assert.hasLength(entity.getLastUpdateIp());
            Assert.hasLength(entity.getLastUpdateDate());

            Assert.notNull(entity.getIsEnabled());
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public void update(DeptStation entity) throws ServiceException {
        validate(entity);
        entity.setLastUpdateUser(em.find(SysUser.class, entity.getLastUpdateUser().getId()));
        em.merge(entity);
    }
}
