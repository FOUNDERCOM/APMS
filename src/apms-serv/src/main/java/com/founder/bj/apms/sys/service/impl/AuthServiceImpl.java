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

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.founder.bj.apms.sys.dto.BureauDTO;
import com.founder.bj.apms.sys.dto.StationDTO;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.founder.bj.apms.sys.dto.ApmsToken;
import com.founder.bj.apms.sys.dto.FuncDTO;
import com.founder.bj.apms.sys.entity.SysFunc;
import com.founder.bj.apms.sys.entity.SysUser;
import com.founder.bj.apms.sys.entity.SysUserAccount;
import com.founder.bj.apms.sys.service.AuthService;
import com.lee.jwaf.token.Func;
import com.lee.jwaf.token.FuncTree;
import com.lee.jwaf.token.Token;
import com.lee.util.BeanUtils;
import com.lee.util.PasswordUtils;

/**
 * Description: 权限服务.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@Transactional
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("unused")
public class AuthServiceImpl implements AuthService {
    /** SerialVersionUID. */
    private static final long serialVersionUID = 6718746124995812410L;

    // CSOFF: MemberName
    /** Hibernate 数据库操作管理器. **/
    @PersistenceContext(unitName = "apms_mgmt")
    private EntityManager em;
    // CSON: MemberName

    @Override
    @Transactional(readOnly = true)
    public String checkAccountAndPwd(String account, String pwd) {
        String hql = "from SysUserAccount as u";
        hql += " where u.account = :account";
        hql += " and u.pwd = :pwd";
        hql += " and u.isEnabled = true";
        final Query query = em.createQuery(hql);
        query.setParameter("account", account);
        query.setParameter("pwd", PasswordUtils.encryptByMD5(pwd));
        //noinspection unchecked
        final List<SysUserAccount> result = query.getResultList();
        return result.size() > 0 ? result.get(0).getId() : null;
    }

    @Override
    public void assignFuncToUser(String userId, String funcId, Boolean assigned) {
        // CSOFF: LineLength
        if (assigned) {
            Query query = em.createNativeQuery("SELECT COUNT(1) FROM APMS_SYS_USER_FUNC WHERE USER_ID = :userId AND FUNC_ID = :funcId");
            final Number count = (Number) query.setParameter("userId", userId).setParameter("funcId", funcId).getSingleResult();
            if (count.intValue() == 0) {
                query = em.createNativeQuery("INSERT INTO APMS_SYS_USER_FUNC(REL_ID, USER_ID, FUNC_ID) VALUES(:id, :userId, :funcId)");
                query.setParameter("id", UUID.randomUUID().toString());
                query.setParameter("userId", userId).setParameter("funcId", funcId).executeUpdate();
            }
        } else {
            final Query query = em.createNativeQuery("DELETE FROM APMS_SYS_USER_FUNC WHERE USER_ID = :userId AND FUNC_ID = :funcId");
            query.setParameter("userId", userId).setParameter("funcId", funcId).executeUpdate();
        }
        // CSON: LineLength
    }

    @Override
    @Transactional(readOnly = true)
    public Token getTokenByUserId(String userId) {
        final ApmsToken token = new ApmsToken();
        final SysUser user = em.find(SysUser.class, userId);
        final SysUserAccount account = em.find(SysUserAccount.class, userId);

        // org
        token.org().setId(user.getStation().getId());
        token.org().setName(user.getStation().getName());
        ((StationDTO) token.org()).setBureau(new BureauDTO());
        ((StationDTO) token.org()).getBureau().setId(user.getStation().getBureau().getId());
        ((StationDTO) token.org()).getBureau().setName(user.getStation().getBureau().getName());
        // user
        token.user().setId(user.getId());
        token.user().setName(user.getName());
        token.user().setOrg(token.org());
        token.user().setAccount(account.getAccount());

        //noinspection unchecked
        final List<String> ids = em.createNativeQuery("SELECT FUNC_ID FROM APMS_SYS_USER_FUNC WHERE USER_ID = :userId")
            .setParameter("userId", user.getId()).getResultList();
        final SysFunc root = em.find(SysFunc.class, "-10000000");
        // function tree
        //noinspection ConstantConditions
        BeanUtils.copyProperties(fetchChildren(root, ids), token.funcTree());
        // function list
        token.funcs().addAll(getFuncList(token.funcTree()));
        return token;
    }

    /**
     * 根据树结构菜单获得菜单列表.
     * @param func 树节点
     * @return 菜单列表
     */
    private List<Func> getFuncList(FuncTree func) {
        final List<Func> result = new LinkedList<>();
        result.add(func);
        if (func.getChildren().size() > 0) {
            for (FuncTree child : func.getChildren()) {
                result.addAll(getFuncList(child));
            }
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> queryFuncIdByUser(String userId) {
        //noinspection unchecked
        return em.createNativeQuery("SELECT FUNC_ID FROM APMS_SYS_USER_FUNC WHERE USER_ID = :userId")
            .setParameter("userId", userId).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public SysFunc getAllFuncByTree() {
        final SysFunc root = em.find(SysFunc.class, -10000000);
        fetchChildren(root);
        return root;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysFunc> queryAllFunc() {
        //noinspection unchecked
        final List<SysFunc> result = em.createQuery("from SysFunc where id <> '-9999999' order by id").getResultList();
        for (SysFunc item : result) {
            item.setLevel(traceLevel(item));
        }
        return result;
    }

    /**
     * Trace the level of the func.
     * @param func func entity
     * @return level
     */
    private int traceLevel(SysFunc func) {
        if (func.getIsRoot()) {
            return -1;
        } else {
            return traceLevel(func.getParent()) + 1;
        }
    }

    /**
     * 从根菜单开始获取数据传输对象.
     * @param node 当前节点
     * @param ids 权限列表
     * @return 权限过滤后的跟菜单数据传输对象
     */
    private FuncDTO fetchChildren(SysFunc node, List<String> ids) {
        if (node.getIsLeaf() && !ids.contains(node.getId())) {
            // 如果是最终功能，而且，不在权限列表中，那么返回空，等待“不”加到列表中
            return null;
        }
        final FuncDTO func = new FuncDTO();
        func.setId(node.getId());
        func.setCode(node.getCode());
        func.setName(node.getName());
        func.setSeq(node.getSeq());
        func.setIcon(node.getIcon());

        func.setParentId(node.getParent().getId());
        func.setChildren(new LinkedList<FuncTree>());
        func.setIsRoot(node.getIsRoot());
        func.setIsLeaf(node.getIsLeaf());

        if (!node.getIsLeaf()) {
            for (SysFunc child : node.getChildren()) {
                final FuncDTO item = fetchChildren(child, ids);
                if (item != null) {
                    func.getChildren().add(item);
                }
            }
        }
        // 如果作为分菜单没有任何子菜单，则返回空，等待“不”加到列表中
        return !func.getIsLeaf() && func.getChildren().size() == 0 && !func.getIsRoot() ? null : func;
    }

    /**
     * 递归获取子节点.
     * @param node 当前节点.
     */
    private void fetchChildren(SysFunc node) {
        if (!node.getIsLeaf()) {
            for (SysFunc child : node.getChildren()) {
                fetchChildren(child);
            }
        }
    }
}
