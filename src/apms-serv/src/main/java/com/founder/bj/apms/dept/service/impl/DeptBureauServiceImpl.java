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

import com.founder.bj.apms.dept.entity.DeptBureau;
import com.founder.bj.apms.dept.service.DeptBureauService;
import com.founder.bj.apms.sys.entity.SysUser;
import com.lee.jwaf.exception.ServiceException;
import com.lee.util.Assert;
import com.lee.util.StringUtils;

// CSOFF: RegexpSinglelineJava
// CSOFF: ParameterName
/**
 * Description: Impl for {@link com.founder.bj.apms.dept.service.DeptBureauService}.<br>
 * Created by Jimmybly Lee on 2017/10/3.
 *
 * @author Jimmybly Lee
 */
@Transactional(readOnly = true)
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DeptBureauServiceImpl implements DeptBureauService {

    private static final long serialVersionUID = -5125368254885440074L;

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    public DeptBureau get(String id) {
        Assert.notNull(id);

        return em.find(DeptBureau.class, id);
    }

    @Override
    public List<DeptBureau> query(DeptBureau condition, Integer start, Integer limit) {
        Assert.notNull(condition);
        Assert.notNull(condition.getIsEnabled());

        String hql = "from DeptBureau as b";
        hql += " left join fetch b.lastUpdateUser";
        hql += " where b.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and b.name like :name";
        }
        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit < 0 ? Integer.MAX_VALUE : limit);
        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("name", "%" + condition.getName() + "%");
        }
        //noinspection unchecked
        return query.getResultList();
    }

    @Override
    public Integer count(DeptBureau condition) {
        Assert.notNull(condition.getIsEnabled());

        String hql = "select count(b) from DeptBureau as b";
        hql += " where b.isEnabled = :isEnabled";
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and b.name like :name";
        }
        final Query query = em.createQuery(hql);
        query.setParameter("isEnabled", condition.getIsEnabled());
        if (!StringUtils.isEmpty(condition.getName())) {
            query.setParameter("name", "%" + condition.getName() + "%");
        }
        return ((Number) query.getSingleResult()).intValue();
    }

    @SuppressWarnings("Duplicates")
    @Transactional
    @Override
    public String create(DeptBureau entity) throws ServiceException {
        entity.setId(null);
        validate(entity);
        entity.setLastUpdateUser(em.find(SysUser.class, entity.getLastUpdateUser().getId()));

        em.persist(entity);
        return entity.getId();
    }

    /**
     * {@link DeptBureau} Entity validator.<br>
     *     validate not null
     *     <ul>
     *         <li>name</li>
     *         <li>strength</li>
     *         <li>stdSalary</li>
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
    private void validate(DeptBureau entity) throws ServiceException {
        try {
            Assert.hasLength(entity.getName(), "名称不能为空。");
            Assert.notNull(entity.getStrength(), "编制不能为空。");
            Assert.notNull(entity.getStdSalary(), "额定薪资不能为空。");

            Assert.hasLength(entity.getContactName(), "联系人姓名不能为空。");
            Assert.hasLength(entity.getContactTel(), "联系人座机不能为空。");
            Assert.hasLength(entity.getContactMobile(), "联系人手机不能为空");

            Assert.notNull(entity.getLastUpdateUser(), "last update user should not be null");
            Assert.notNull(entity.getLastUpdateUser().getId(), "last update user id should not be null");
            final SysUser updater = em.find(SysUser.class, entity.getLastUpdateUser().getId());
            Assert.notNull(updater.getName(), "can not find last update user by given id");
            Assert.hasLength(entity.getLastUpdateIp(), "last update ip should not be null");
            Assert.hasLength(entity.getLastUpdateDate(), "last update date should not be null");

            Assert.notNull(entity.getIsEnabled(), "isEnabled should not be null");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public void update(DeptBureau entity) throws ServiceException {
        validate(entity);
        entity.setLastUpdateUser(em.find(SysUser.class, entity.getLastUpdateUser().getId()));
        em.merge(entity);
    }
}
