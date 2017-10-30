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

package com.founder.bj.apms.sys.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * Description: 系统用户账号密码.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_SYS_USER")
@SuppressWarnings("unused")
public class SysUserAccount implements Serializable {

    private static final long serialVersionUID = -5471531381494696049L;
    /** Id. */
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** 用户帐号. */
    @Column(name = "USER_ACCOUNT")
    private String account;

    /** 用户密码. */
    @Column(name = "USER_PWD")
    private String pwd;

    /** 是否可用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    /**
     * Get the id.
     *
     * @return return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the account.
     *
     * @return return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * Set account.
     *
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * Get the pwd.
     *
     * @return return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Set pwd.
     *
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Get the isEnabled.
     *
     * @return return the isEnabled
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * Set isEnabled.
     *
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
