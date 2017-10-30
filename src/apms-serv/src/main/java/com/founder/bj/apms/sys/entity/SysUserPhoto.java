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
 * Description: 系统用户照片.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_SYS_USER")
@SuppressWarnings("unused")
public class SysUserPhoto implements Serializable {

    private static final long serialVersionUID = 3194452506187802623L;

    /**
     * Id.
     */
    @Id
    @Column(name = "USER_ID")
    @GenericGenerator(name = "foreignKey", strategy = "foreign",
        parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "user")})
    @GeneratedValue(generator = "foreignKey")
    private String id;

    /**
     * 用户头像.
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "USER_PHOTO", columnDefinition = "CLOB")
    private String data;

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
     * Get the data.
     *
     * @return return the data
     */
    public String getData() {
        return data;
    }

    /**
     * Set data.
     *
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
}
