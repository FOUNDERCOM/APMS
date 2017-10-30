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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

/**
 * Description: 字典实体类 for 编辑.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_SYS_DICT")
@SuppressWarnings("unused")
public class SysDictionary implements Serializable {

    private static final long serialVersionUID = 3229911880935274561L;
    /** Id. */
    @Id
    @Column(name = "DICT_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** 上级字典，往往直接是类型描述，除非是父子结构的. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DICT_PARENT_ID")
    private SysDictionary parent;

    /** 类型. */
    @Column(name = "DICT_NATURE")
    private String nature;

    /** 编码. */
    @OrderBy
    @Column(name = "DICT_CODE")
    private String code;

    /** 显示值. */
    @Column(name = "DICT_VALUE")
    private String value;

    /** 描述，只有类型描述的项需要填写这个. */
    @Column(name = "DICT_DESC")
    private String desc;

    /** 是否禁用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    // CSOFF: LineLength
    /** 是否有下级. */
    @Formula("(SELECT CASE WHEN (COUNT(1) > 0) THEN 1 ELSE 0 END FROM APMS_SYS_DICT DICT WHERE DICT.DICT_PARENT_ID = DICT_ID)")
    private Boolean hasChildren;
    // CSON: LineLength

    /** 是否是“类型”而非具体字典项. */
    @Formula("(CASE WHEN (DICT_CODE = 'NATURE_TYPE') THEN 1 ELSE 0 END)")
    private Boolean isNature;

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
     * Get the parent.
     *
     * @return return the parent
     */
    public SysDictionary getParent() {
        return parent;
    }

    /**
     * Set parent.
     *
     * @param parent the parent to set
     */
    public void setParent(SysDictionary parent) {
        this.parent = parent;
    }

    /**
     * Get the nature.
     *
     * @return return the nature
     */
    public String getNature() {
        return nature;
    }

    /**
     * Set nature.
     *
     * @param nature the nature to set
     */
    public void setNature(String nature) {
        this.nature = nature;
    }

    /**
     * Get the code.
     *
     * @return return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set code.
     *
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the value.
     *
     * @return return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set value.
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
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

    /**
     * Get the hasChildren.
     *
     * @return return the hasChildren
     */
    public Boolean getHasChildren() {
        return hasChildren;
    }

    /**
     * Set hasChildren.
     *
     * @param hasChildren the hasChildren to set
     */
    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * Get the isNature.
     *
     * @return return the isNature
     */
    public Boolean getIsNature() {
        return isNature;
    }

    /**
     * Set isNature.
     *
     * @param isNature the isNature to set
     */
    public void setIsNature(Boolean isNature) {
        this.isNature = isNature;
    }
}
