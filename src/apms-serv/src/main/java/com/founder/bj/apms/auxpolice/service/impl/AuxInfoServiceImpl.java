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

package com.founder.bj.apms.auxpolice.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.founder.bj.apms.auxpolice.entity.AuxAppraise;
import com.founder.bj.apms.auxpolice.entity.AuxEdu;
import com.founder.bj.apms.auxpolice.entity.AuxInfo;
import com.founder.bj.apms.auxpolice.entity.AuxStuff;
import com.founder.bj.apms.auxpolice.service.AuxInfoService;
import com.founder.bj.apms.dept.entity.DeptStation;
import com.founder.bj.apms.sys.entity.SysDict;
import com.founder.bj.apms.sys.entity.SysUser;
import com.founder.bj.apms.sys.service.DictService;
import com.lee.jwaf.exception.ServiceException;
import com.lee.jwaf.token.Token;
import com.lee.util.Assert;
import com.lee.util.DateUtils;
import com.lee.util.ObjectUtils;
import com.lee.util.StringUtils;

// CSOFF: RegexpSinglelineJava
// CSOFF: ParameterName
/**
 * Description: Impl for {@link AuxInfoService}.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
@Transactional(readOnly = true)
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuxInfoServiceImpl implements AuxInfoService {

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    /** DictService. */
    @Resource
    private DictService dictService;

    @Override
    public AuxInfo get(String id) {
        return em.find(AuxInfo.class, id);
    }

    @Override
    public List<AuxInfo> queryAll(AuxInfo condition) {
        String hql = "";
        hql = "from AuxInfo i";
        hql += " left join fetch i.station";
        hql += " left join fetch i.station.bureau";
        hql += " left join fetch i.oldIdentity";
        hql += " left join fetch i.sex";
        hql += " left join fetch i.nation";
        hql += " left join fetch i.health";
        hql += " left join fetch i.politicalStatus";
        hql += " left join fetch i.eduDegree";
        hql += " where i.isEnabled = true";

        if (!ObjectUtils.isEmpty(condition.getStation()) && !ObjectUtils.isEmpty(condition.getStation().getBureau())
            && !ObjectUtils.isEmpty(condition.getStation().getBureau().getId())) {
            hql += " and i.station.bureau.id = :bureauId";
        }

        hql += " order by i.station.bureau.id, i.station.id, i.age";

        //noinspection unchecked
        Query query = em.createQuery(hql);
        if (!ObjectUtils.isEmpty(condition.getStation()) && !ObjectUtils.isEmpty(condition.getStation().getBureau())
            && !ObjectUtils.isEmpty(condition.getStation().getBureau().getId())) {
            query.setParameter("bureauId", condition.getStation().getBureau().getId());
        }
        return query.getResultList();
    }

    @Override
    public List<AuxInfo> query(AuxInfo condition, Integer start, Integer limit) {
        String hql = " select i.id from AuxInfo i";

        hql += makeQuery(condition, null);

        final Query query = em.createQuery(hql);
        query.setFirstResult(start).setMaxResults(limit > 0 ? limit : Integer.MAX_VALUE);

        makeQuery(condition, query);

        //noinspection unchecked
        final List<String> idList = query.getResultList();

        hql = "from AuxInfo i";
        hql += " left join fetch i.station";
        hql += " left join fetch i.station.bureau";
        hql += " left join fetch i.sex";
        hql += " left join fetch i.nation";
        hql += " left join fetch i.health";
        hql += " left join fetch i.politicalStatus";
        hql += " left join fetch i.eduDegree";
        hql += " left join fetch i.status";
        hql += " left join fetch i.lastUpdateUser";
        hql += " left join fetch i.lastApproveUser";
        hql += " left join fetch i.awardList";
        hql += " left join fetch i.eduList";
        hql += " left join fetch i.familyList";
        hql += " left join fetch i.punishList";
        hql += " left join fetch i.workList";
        hql += " left join fetch i.fileList";
        hql += " left join fetch i.appraiseList";
        hql += " left join fetch i.status";
        hql += " where i.id in (:idList)";
//        hql += " order by i.station.id";

        //noinspection unchecked
        List<AuxInfo> list = idList.size() == 0?Collections.EMPTY_LIST:em.createQuery(hql).setParameter("idList", idList).getResultList();
        //清理重复辅警
        int size = list.size();
        if(size != 0){
        	HashMap<String, AuxInfo> map = new HashMap<String, AuxInfo>();
        	for (int i = 0; i < size; i++) {
        		map.put(list.get(i).getId(), list.get(i));
    		}

        	list = new ArrayList<AuxInfo>();
        	for(String key : map.keySet()){
        		list.add(map.get(key));
        	}
        }
        return list;
    }

    /**
     * Make query or HQL.
     * @param condition condition
     * @param query query may null
     * @return hql;
     */
    @SuppressWarnings({"CheckStyle", "ConstantConditions"})
    private String makeQuery(AuxInfo condition, Query query) {
        String hql = "";
        hql += " where i.isEnabled = :isEnabled";

        if (!ObjectUtils.isEmpty(query)) {
            query.setParameter("isEnabled", condition.getIsEnabled());
        }
        // 姓名
        if (!StringUtils.isEmpty(condition.getName())) {
            hql += " and i.name like :name";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("name", "%" + condition.getName() + "%");
            }
        }
        // 身份证号
        if (!StringUtils.isEmpty(condition.getIdentityCard())) {
            hql += " and i.identityCard like :identityCard";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("identityCard", condition.getIdentityCard() + "%");
            }
        }
        // 性别
        if (!ObjectUtils.isEmpty(condition.getSex()) && !StringUtils.isEmpty(condition.getSex().getCode())) {
            hql += " and i.sex.code = :sexCode";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("sexCode", condition.getSex().getCode());
            }
        }
        // 年龄 大于
        if (!StringUtils.isEmpty(condition.getBirthday())) {
            hql += " and i.birthday > :birthday";

            if (!ObjectUtils.isEmpty(query)) {
                final Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR, -Integer.parseInt(condition.getBirthday()));
                query.setParameter("birthday", DateUtils.format(c.getTime(), "yyyy-MM-dd"));
            }
        }
        // 民族
        if (!ObjectUtils.isEmpty(condition.getNation()) && !StringUtils.isEmpty(condition.getNation().getCode())) {
            hql += " and i.nation.code = :nationCode";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("nationCode", condition.getNation().getCode());
            }
        }
        // 健康状况
        if (!ObjectUtils.isEmpty(condition.getHealth()) && !StringUtils.isEmpty(condition.getHealth().getCode())) {
            hql += " and i.health.code = :healthCode";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("healthCode", condition.getHealth().getCode());
            }
        }
        // 政治面貌
        if (!ObjectUtils.isEmpty(condition.getPoliticalStatus())
            && !StringUtils.isEmpty(condition.getPoliticalStatus().getCode())) {
            hql += " and i.politicalStatus.code = :politicalStatusCode";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("healthCode", condition.getPoliticalStatus().getCode());
            }
        }
        // 学位
        if (!ObjectUtils.isEmpty(condition.getEduDegree())
            && !StringUtils.isEmpty(condition.getEduDegree().getCode())) {
            hql += " and i.eduDegree.code = :eduDegreeCode";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("eduDegreeCode", condition.getEduDegree().getCode());
            }
        }
        // 工龄
        if (!StringUtils.isEmpty(condition.getJoinDate())) {
            hql += " and i.joinDate > :joinDate";

            if (!ObjectUtils.isEmpty(query)) {
                final Calendar c = Calendar.getInstance();
                c.add(Calendar.YEAR, -Integer.parseInt(condition.getJoinDate()));
                query.setParameter("joinDate", DateUtils.format(c.getTime(), "yyyy-MM-dd"));
            }
        }
        // 薪资是否正常
        if (!ObjectUtils.isEmpty(condition.getIsSalaryNormal())) {
            hql += " and i.isSalaryNormal = :isSalaryNormal";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("isSalaryNormal", condition.getIsSalaryNormal());
            }
        }
        // 所在分局
        if (!ObjectUtils.isEmpty(condition.getStation()) && !ObjectUtils.isEmpty(condition.getStation().getBureau())
            && !ObjectUtils.isEmpty(condition.getStation().getBureau().getId())) {
            hql += " and i.station.bureau.id = :bureauId";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("bureauId", condition.getStation().getBureau().getId());
            }
        }
        // 所在派出所
        if (!ObjectUtils.isEmpty(condition.getStation()) && !ObjectUtils.isEmpty(condition.getStation().getId())) {
            hql += " and i.station.id = :stationId";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("stationId", condition.getStation().getId());
            }
        }
        // 审核状态
        if (!ObjectUtils.isEmpty(condition.getStatus()) && !StringUtils.isEmpty(condition.getStatus().getCode())) {
            hql += " and i.status.code = :statusCode";

            if (!ObjectUtils.isEmpty(query)) {
                query.setParameter("statusCode", condition.getStatus().getCode());
            }
        }
        return hql;
    }

    @Override
    public Integer count(AuxInfo condition) {
        String hql = "select count(i) from AuxInfo i";

        hql += makeQuery(condition, null);

        final Query query = em.createQuery(hql);

        makeQuery(condition, query);

        //noinspection unchecked
        return ((Number) query.getSingleResult()).intValue();
    }

    @Transactional
    @Override
    public String create(AuxInfo entity) throws ServiceException {
        entity.setIsEnabled(true);
        entity.setSalaryBase(0);
        entity.setSalaryBonus(0);
        entity.setSalaryTax(0);
        entity.setSalarySSS(0);
        entity.setSalarySFund(0);
        entity.setSalaryCSS(0);
        entity.setSalaryCFund(0);
        entity.setStatus(dictService.getSysDictByNatureAndCode("PROCESS_STATUS", "TO_APPLY"));

        validate(entity);

        fillEntity(entity);

        em.persist(entity);

        return entity.getId();
    }

    @Transactional
    @Override
    public void update(AuxInfo entity) throws ServiceException {
        validate(entity);

        fillEntity(entity);

        em.merge(entity);
    }

    /**
     * Fill the entity with persisted stuff entities.
     * @param entity the {@link AuxInfo} entity
     */
    private void fillEntity(AuxInfo entity) {
        setInfo(entity.getAwardList(), entity);
        setInfo(entity.getEduList(), entity);
        setInfo(entity.getFamilyList(), entity);
        setInfo(entity.getPunishList(), entity);
        setInfo(entity.getWorkList(), entity);
        setInfo(entity.getFileList(), entity);
        setInfo(entity.getAppraiseList(), entity);

        for (AuxEdu item : entity.getEduList()) {
            if (!ObjectUtils.isEmpty(item.getDegree())) {
                item.setDegree(em.find(SysDict.class, item.getDegree().getId()));
            }
        }

        for (AuxAppraise item : entity.getAppraiseList()) {
            if (!ObjectUtils.isEmpty(item.getLevel())) {
                item.setLevel(em.find(SysDict.class, item.getLevel().getId()));
            }
        }

        entity.setSex(dictService.getSysDictByNatureAndCode("SEX", entity.getSex().getCode()));
        entity.setOldIdentity(em.find(SysDict.class, entity.getOldIdentity().getId()));
        entity.setNation(em.find(SysDict.class, entity.getNation().getId()));
        entity.setHealth(em.find(SysDict.class, entity.getHealth().getId()));
        entity.setPoliticalStatus(em.find(SysDict.class, entity.getPoliticalStatus().getId()));
        entity.setEduDegree(em.find(SysDict.class, entity.getEduDegree().getId()));
        entity.setStatus(em.find(SysDict.class, entity.getStatus().getId()));
        entity.setStation(em.find(DeptStation.class, entity.getStation().getId()));
        entity.setLastUpdateUser(em.find(SysUser.class, entity.getLastUpdateUser().getId()));
        entity.setLastApproveUser(ObjectUtils.isEmpty(entity.getLastApproveUser())
            ? null : em.find(SysUser.class, entity.getLastApproveUser().getId()));

    }

    /**
     * Rejoin the {@link AuxStuff} with the {@link AuxInfo}.
     * @param list stuff list
     * @param aux aux
     */
    private void setInfo(Set<? extends AuxStuff> list, AuxInfo aux) {
        for (AuxStuff item : list) {
            item.setAux(aux);
            item.setLastUpdateUser(em.find(SysUser.class, aux.getLastUpdateUser().getId()));
            item.setLastUpdateDate(aux.getLastUpdateDate());
            item.setLastUpdateIp(aux.getLastUpdateIp());
        }
    }

    /**
     * Validate entity.
     * @param entity the {@link AuxInfo}
     *
     * @throws ServiceException validation fall
     */
    private void validate(AuxInfo entity) throws ServiceException {
        try {
            Assert.notNull(entity);
            Assert.notNull(entity.getName(), "名称不能为空。");
            Assert.notNull(entity.getMobile(), "手机不能为空。");
            Assert.notNull(entity.getIdentityCard(), "身份证号不能为空。");
            Assert.notNull(entity.getOldIdentity(), "入职前身份不能为空。");
            Assert.notNull(entity.getBirthday(), "生日不能为空。");
            Assert.notNull(entity.getNation(), "民族不能为空。");
            Assert.notNull(entity.getHealth(), "健康状况不能为空。");
            Assert.notNull(entity.getPoliticalStatus(), "政治面貌不能为空。");
            Assert.notNull(entity.getEduDegree(), "学位不能为空。");
            Assert.notNull(entity.getInstitutions(), "毕业院校不能为空。");
            Assert.notNull(entity.getJob(), "职位不能为空。");
            Assert.notNull(entity.getJoinDate(), "入职时间不能为空。");
            Assert.notNull(entity.getAddProvince(), "住地省份不能为空。");
            Assert.notNull(entity.getAddCity(), "住地城市不能为空。");
            Assert.notNull(entity.getAddCountry(), "住地县/街道不能为空。");
            Assert.notNull(entity.getAddDetail(), "住地详址不能为空。");
            Assert.notNull(entity.getAddPostcode(), "住地邮编不能为空。");

            Assert.notNull(entity.getPhoto());
            Assert.notNull(entity.getStatus());
            Assert.notNull(entity.getStation());
            Assert.notNull(entity.getLastUpdateUser());
            Assert.notNull(entity.getLastUpdateDate());
            Assert.notNull(entity.getLastUpdateIp());
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public void removeStuff(String id, Class<? extends AuxStuff> type) {
        em.remove(em.find(type, id));
    }

    @Transactional
    @Override
    public void setStatus(Token token, String ip, String id, Boolean isEnabled) {
        final AuxInfo entity = get(id);

        entity.setLastUpdateUser(em.find(SysUser.class, token.user().getId()));
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
        entity.setLastUpdateIp(ip);
        if(isEnabled){
          entity.setStatus(dictService.getSysDictByNatureAndCode("PROCESS_STATUS", "TO_APPLY"));
        }
        entity.setIsEnabled(isEnabled);
    }

    @Transactional
    @Override
    public void changeSalary(Token token, String ip, String id, Integer base, Integer bonus, Integer tax, Integer sss, Integer sFund, Integer css, Integer cFund) {
        final AuxInfo entity = get(id);

        entity.setLastUpdateUser(em.find(SysUser.class, token.user().getId()));
        entity.setLastUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
        entity.setLastUpdateIp(ip);

        entity.setSalaryBase(base);
        entity.setSalaryBonus(bonus);
        entity.setSalaryTax(tax);
        entity.setSalarySSS(sss);
        entity.setSalarySFund(sFund);
        entity.setSalaryCSS(css);
        entity.setSalaryCFund(cFund);

        entity.setSalarySGet(base + bonus - tax - sss - sFund);
        entity.setSalaryCPay(base + bonus + css + cFund);
    }

    @Override
    public boolean checkDuplicatedIdentityCare(String id, String card) {
        if (ObjectUtils.isEmpty(id)) {
            return em.createQuery("from AuxInfo where identityCard = :card")
                .setParameter("card", card).getResultList().size() > 0;
        } else {
            return em.createQuery("from AuxInfo where identityCard = :card and id != :id")
                .setParameter("card", card).setParameter("id", id).getResultList().size() > 0;
        }
    }
}
