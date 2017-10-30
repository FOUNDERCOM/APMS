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

package com.founder.bj.apms.sys.dto;

import java.io.Serializable;

/**
 * Description: DTO for {@link com.founder.bj.apms.dept.entity.DeptBureau}.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
public class BureauDTO implements Serializable {

    private static final long serialVersionUID = 2494748680229395013L;

    /** ID. */
    private String id;
    /** 名称. */
    private String name;
    /** 编制. */
    private Integer strength;
    /** 辅警额定工资. */
    private Integer stdSalary;

    /** 联系人姓名. */
    private String contactName;
    /** 联系人的座机. */
    private String contactTel;
    /** 联系人的手机. */
    private String contactMobile;
    /** 联系人的邮箱. */
    private String contactMail;

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
     * Get the strength.
     *
     * @return return the strength
     */
    public Integer getStrength() {
        return strength;
    }

    /**
     * Set strength.
     *
     * @param strength the strength to set
     */
    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    /**
     * Get the stdSalary.
     *
     * @return return the stdSalary
     */
    public Integer getStdSalary() {
        return stdSalary;
    }

    /**
     * Set stdSalary.
     *
     * @param stdSalary the stdSalary to set
     */
    public void setStdSalary(Integer stdSalary) {
        this.stdSalary = stdSalary;
    }

    /**
     * Get the contactName.
     *
     * @return return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Set contactName.
     *
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Get the contactTel.
     *
     * @return return the contactTel
     */
    public String getContactTel() {
        return contactTel;
    }

    /**
     * Set contactTel.
     *
     * @param contactTel the contactTel to set
     */
    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    /**
     * Get the contactMobile.
     *
     * @return return the contactMobile
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * Set contactMobile.
     *
     * @param contactMobile the contactMobile to set
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    /**
     * Get the contactMail.
     *
     * @return return the contactMail
     */
    public String getContactMail() {
        return contactMail;
    }

    /**
     * Set contactMail.
     *
     * @param contactMail the contactMail to set
     */
    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }
}
