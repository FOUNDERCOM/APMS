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

package com.founder.bj.apms.auxpolice.entity;

import javax.persistence.*;

import com.founder.bj.apms.sys.entity.SysUser;

/**
 * Description: Aux Punish Entity.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_AUX_PUNISH")
@SuppressWarnings("unused")
public class AuxPunish implements AuxStuff {

    private static final long serialVersionUID = 5419252734650919933L;

    /** Id. */
    @Id
    @Column(name = "PUNISH_ID")
    @SequenceGenerator(name = "apmsSEQ", sequenceName = "SEQ_APMS")
    @GeneratedValue(generator = "apmsSEQ", strategy = GenerationType.SEQUENCE)
    private Integer id;

    /** Department name. */
    @Column(name = "PUNISH_DEPT")
    private String dept;

    /** Punish date. */
    @OrderBy
    @Column(name = "PUNISH_DATE")
    private String date;

    /** Punish title. */
    @Column(name = "PUNISH_TITLE")
    private String title;

    /** Punish description or reason. */
    @Column(name = "PUNISH_DESC")
    private String desc;

    /** 最近更新人ID与用户表关联. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LATEST_UPDATE_USER")
    private SysUser lastUpdateUser;
    /** 最近更新时间. */
    @Column(name = "LATEST_UPDATE_DATE")
    private String lastUpdateDate;
    /** 最近更新IP. */
    @Column(name = "LATEST_UPDATE_IP")
    private String lastUpdateIp;

    /** Aux. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUX_ID")
    private AuxInfo aux;

    /**
     * Get the id.
     *
     * @return return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the dept.
     *
     * @return return the dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * Set dept.
     *
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * Get the date.
     *
     * @return return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set date.
     *
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the title.
     *
     * @return return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the desc.
     *
     * @return return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Set desc.
     *
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Get the lastUpdateUser.
     *
     * @return return the lastUpdateUser
     */
    public SysUser getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * Set lastUpdateUser.
     *
     * @param lastUpdateUser the lastUpdateUser to set
     */
    public void setLastUpdateUser(SysUser lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * Get the lastUpdateDate.
     *
     * @return return the lastUpdateDate
     */
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Set lastUpdateDate.
     *
     * @param lastUpdateDate the lastUpdateDate to set
     */
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Get the lastUpdateIp.
     *
     * @return return the lastUpdateIp
     */
    public String getLastUpdateIp() {
        return lastUpdateIp;
    }

    /**
     * Set lastUpdateIp.
     *
     * @param lastUpdateIp the lastUpdateIp to set
     */
    public void setLastUpdateIp(String lastUpdateIp) {
        this.lastUpdateIp = lastUpdateIp;
    }

    /**
     * Get the aux.
     *
     * @return return the aux
     */
    public AuxInfo getAux() {
        return aux;
    }

    /**
     * Set aux.
     *
     * @param aux the aux to set
     */
    public void setAux(AuxInfo aux) {
        this.aux = aux;
    }
}
