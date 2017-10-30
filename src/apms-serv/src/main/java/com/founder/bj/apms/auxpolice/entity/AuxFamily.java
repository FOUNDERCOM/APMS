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
 * Description: Aux Family Entity.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_AUX_FAMILY")
@SuppressWarnings("unused")
public class AuxFamily implements AuxStuff {

    private static final long serialVersionUID = -7213954845865873610L;

    /** Id. */
    @Id
    @Column(name = "FAMILY_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** Relationship to the current person. */
    @Column(name = "FAMILY_REL")
    private String rel;

    /** Name. */
    @Column(name = "FAMILY_NAME")
    private String name;

    /** Birthday. */
    @Column(name = "FAMILY_BIRTHDAY")
    private String birthday;

    /** Mobile. */
    @Column(name = "FAMILY_MOBILE")
    private String mobile;

    /** Identity card. */
    @Column(name = "FAMILY_IDENTITY_CARD")
    private String identityCard;

    /** Work department name. */
    @Column(name = "FAMILY_DEPT")
    private String dept;

    /** Jon name. */
    @Column(name = "FAMILY_JOB")
    private String job;

    /** Native place. */
    @Column(name = "FAMILY_NATIVE_PLACE")
    private String nativePlace;

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
     * Get the rel.
     *
     * @return return the rel
     */
    public String getRel() {
        return rel;
    }

    /**
     * Set rel.
     *
     * @param rel the rel to set
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * Get the name.
     *
     * @return return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the birthday.
     *
     * @return return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Set birthday.
     *
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Get the mobile.
     *
     * @return return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Set mobile.
     *
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Get the identityCard.
     *
     * @return return the identityCard
     */
    public String getIdentityCard() {
        return identityCard;
    }

    /**
     * Set identityCard.
     *
     * @param identityCard the identityCard to set
     */
    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
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
     * Get the nativePlace.
     *
     * @return return the nativePlace
     */
    public String getNativePlace() {
        return nativePlace;
    }

    /**
     * Set nativePlace.
     *
     * @param nativePlace the nativePlace to set
     */
    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
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
