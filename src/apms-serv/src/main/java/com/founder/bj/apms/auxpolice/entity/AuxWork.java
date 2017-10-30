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

import org.hibernate.annotations.GenericGenerator;

import com.founder.bj.apms.sys.entity.SysUser;

/**
 * Description: Aux Work Entity.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_AUX_WORK")
@SuppressWarnings("unused")
public class AuxWork implements AuxStuff {

    private static final long serialVersionUID = 3938721311908893826L;

    /** Id. */
    @Id
    @Column(name = "WORK_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** Work for department name. */
    @Column(name = "WORK_DEPT")
    private String dept;

    /** Job title. */
    @Column(name = "WORK_JOB")
    private String job;

    /** Start date. */
    @OrderBy
    @Column(name = "WORK_START")
    private String start;

    /** End date. */
    @Column(name = "WORK_END")
    private String end;

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
    @ManyToOne
    @JoinColumn(name = "AUX_ID")
    private AuxInfo aux;

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
     * Get the job.
     *
     * @return return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * Set job.
     *
     * @param job the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Get the start.
     *
     * @return return the start
     */
    public String getStart() {
        return start;
    }

    /**
     * Set start.
     *
     * @param start the start to set
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Get the end.
     *
     * @return return the end
     */
    public String getEnd() {
        return end;
    }

    /**
     * Set end.
     *
     * @param end the end to set
     */
    public void setEnd(String end) {
        this.end = end;
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
