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

package com.founder.bj.apms.att.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.founder.bj.apms.sys.entity.SysUser;

/**
 * Description: 附件实体.<br>
 * Created by Jimmybly Lee on 2017/10/18.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_ATTACHMENT")
@SuppressWarnings("unused")
public class Attachment implements Serializable {

    private static final long serialVersionUID = -4499442196262178017L;

    /** Id. */
    @Id
    @Column(name = "ATT_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** Name. */
    @Column(name = "ATT_NAME")
    private String name;

    /** Type. */
    @Column(name = "ATT_TYPE")
    private String type;

    /** Suffix.*/
    @Column(name = "ATT_SUFFIX")
    private String suffix;

    /** Size. */
    @Column(name = "ATT_SIZE")
    private Long size;

    /** Data. */
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "ATT_DATA")
    private byte[] data;

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
     * Get the type.
     *
     * @return return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set type.
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the suffix.
     *
     * @return return the suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Set suffix.
     *
     * @param suffix the suffix to set
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Get the size.
     *
     * @return return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * Set size.
     *
     * @param size the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * Get the data.
     *
     * @return return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Set data.
     *
     * @param data the data to set
     */
    public void setData(byte[] data) {
        this.data = data;
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
}
