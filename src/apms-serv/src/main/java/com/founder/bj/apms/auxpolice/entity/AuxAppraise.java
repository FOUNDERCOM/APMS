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
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.founder.bj.apms.sys.entity.SysDict;
import com.founder.bj.apms.sys.entity.SysUser;

/**
 * Description: 年度评价.<br>
 * Created by Jimmybly Lee on 2017/10/23.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_AUX_APPRAISE")
@SuppressWarnings("unused")
public class AuxAppraise implements AuxStuff {

    private static final long serialVersionUID = -2214232082000645568L;
    /** Id. */
    @Id
    @Column(name = "APPR_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** Year period. */
    @Column(name = "APPR_YEAR")
    private String year;

    /** Appraise Level. */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "APPR_LEVEL", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'APPRAISE_LEVEL'")) })
    private SysDict level;
    // CSON: LineLength

    /** Description. */
    @Column(name = "APPR_DESC")
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
    @ManyToOne
    @JoinColumn(name = "AUX_ID")
    private AuxInfo aux;

    /**
     * Get the id.
     *
     * @return return the id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id the id to set
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the year.
     *
     * @return return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Set year.
     *
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Get the level.
     *
     * @return return the level
     */
    public SysDict getLevel() {
        return level;
    }

    /**
     * Set level.
     *
     * @param level the level to set
     */
    public void setLevel(SysDict level) {
        this.level = level;
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
    @Override
    public SysUser getLastUpdateUser() {
        return lastUpdateUser;
    }

    /**
     * Set lastUpdateUser.
     *
     * @param lastUpdateUser the lastUpdateUser to set
     */
    @Override
    public void setLastUpdateUser(SysUser lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    /**
     * Get the lastUpdateDate.
     *
     * @return return the lastUpdateDate
     */
    @Override
    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * Set lastUpdateDate.
     *
     * @param lastUpdateDate the lastUpdateDate to set
     */
    @Override
    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Get the lastUpdateIp.
     *
     * @return return the lastUpdateIp
     */
    @Override
    public String getLastUpdateIp() {
        return lastUpdateIp;
    }

    /**
     * Set lastUpdateIp.
     *
     * @param lastUpdateIp the lastUpdateIp to set
     */
    @Override
    public void setLastUpdateIp(String lastUpdateIp) {
        this.lastUpdateIp = lastUpdateIp;
    }

    /**
     * Get the aux.
     *
     * @return return the aux
     */
    @Override
    public AuxInfo getAux() {
        return aux;
    }

    /**
     * Set aux.
     *
     * @param aux the aux to set
     */
    @Override
    public void setAux(AuxInfo aux) {
        this.aux = aux;
    }
}
