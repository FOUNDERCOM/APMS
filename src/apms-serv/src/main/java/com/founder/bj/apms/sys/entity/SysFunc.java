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
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.*;

/**
 * Description: 功能菜单实体类.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@Entity
@Table(name = "APMS_SYS_FUNC")
@SuppressWarnings("unused")
public class SysFunc implements Serializable {

    private static final long serialVersionUID = -8861215077537994432L;
    /** Id. */
    @Id
    @Column(name = "FUNC_ID")
    @GeneratedValue(generator = "apms_uuid")
    @GenericGenerator(name = "apms_uuid", strategy = "uuid2")
    private String id;

    /** 上级功能. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNC_PARENT_ID")
    private SysFunc parent;

    /** 功能编码，前台需要解析. */
    @Column(name = "FUNC_CODE")
    private String code;

    /** 功能需要显示的名称. */
    @Column(name = "FUNC_NAME")
    private String name;

    /** 功能类型，ROOT(根),GROUP(分组),MENU(菜单),LINK(链接),FUNC(功能菜单),ACTION(行为). */
    // CSOFF: LineLength
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumnsOrFormulas({
        @JoinColumnOrFormula(column = @JoinColumn(name = "FUNC_TYPE", referencedColumnName = "DICT_CODE", nullable = false)),
        @JoinColumnOrFormula(formula = @JoinFormula(referencedColumnName = "DICT_NATURE", value = "'FUNC_TYPE'")) })
    private SysDict type;

    /** 如果是连接，则设置此URL. */
    @Column(name = "FUNC_URL")
    private String url;

    /** 功能说明. */
    @Column(name = "FUNC_DESC")
    private String desc;

    /** 功能在同级排序的序号. */
    @Column(name = "FUNC_SEQ")
    @OrderBy
    private Integer seq;

    /** 功能在显示时，同时需要显示的图标的css样式. */
    @Column(name = "FUNC_ICON")
    private String icon;

    /** 是否内嵌，针对LINK(链接),FUNC(功能菜单)关联的页面是否是内嵌到屏主功能页面内，如果不是，则弹出. */
    @Column(name = "FUNC_IS_BUILT_IN")
    private Boolean isBuiltIn;

    /** 功能是否被启用. */
    @Column(name = "IS_ENABLED")
    private Boolean isEnabled;

    /** 功能是否可见. */
    @Column(name = "IS_VISIBLE")
    private Boolean isVisible;

    /** 下级功能. */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<SysFunc> children;

    /** 是否为根节点. */
    @Formula("(CASE WHEN (FUNC_PARENT_ID = '0') THEN 1 ELSE 0 END)")
    private Boolean isRoot;

    // CSOFF: LineLength
    /** 是否为叶子节点. */
    @Formula("(SELECT CASE WHEN (COUNT(1) > 0) THEN 0 ELSE 1 END FROM APMS_SYS_FUNC FUNC WHERE FUNC.FUNC_PARENT_ID = FUNC_ID)")
    private Boolean isLeaf;
    // CSON: LineLength

    /** 是否已经被赋予. */
    @Transient
    private Boolean isAssigned = false;

    /** 层级. */
    @Transient
    private Integer level;

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
    public SysFunc getParent() {
        return parent;
    }

    /**
     * Set parent.
     *
     * @param parent the parent to set
     */
    public void setParent(SysFunc parent) {
        this.parent = parent;
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
    public SysDict getType() {
        return type;
    }

    /**
     * Set type.
     *
     * @param type the type to set
     */
    public void setType(SysDict type) {
        this.type = type;
    }

    /**
     * Get the url.
     *
     * @return return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set url.
     *
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * Get the seq.
     *
     * @return return the seq
     */
    public Integer getSeq() {
        return seq;
    }

    /**
     * Set seq.
     *
     * @param seq the seq to set
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * Get the icon.
     *
     * @return return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Set icon.
     *
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Get the isBuiltIn.
     *
     * @return return the isBuiltIn
     */
    public Boolean getIsBuiltIn() {
        return isBuiltIn;
    }

    /**
     * Set isBuiltIn.
     *
     * @param isBuiltIn the isBuiltIn to set
     */
    public void setIsBuiltIn(Boolean isBuiltIn) {
        this.isBuiltIn = isBuiltIn;
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
     * Get the isVisible.
     *
     * @return return the isVisible
     */
    public Boolean getIsVisible() {
        return isVisible;
    }

    /**
     * Set isVisible.
     *
     * @param isVisible the isVisible to set
     */
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Get the children.
     *
     * @return return the children
     */
    public List<SysFunc> getChildren() {
        return children;
    }

    /**
     * Set children.
     *
     * @param children the children to set
     */
    public void setChildren(List<SysFunc> children) {
        this.children = children;
    }

    /**
     * Get the isRoot.
     *
     * @return return the isRoot
     */
    public Boolean getIsRoot() {
        return isRoot;
    }

    /**
     * Set isRoot.
     *
     * @param isRoot the isRoot to set
     */
    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    /**
     * Get the isLeaf.
     *
     * @return return the isLeaf
     */
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    /**
     * Set isLeaf.
     *
     * @param isLeaf the isLeaf to set
     */
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * Get the isAssigned.
     *
     * @return return the isAssigned
     */
    public Boolean getIsAssigned() {
        return isAssigned;
    }

    /**
     * Set isAssigned.
     *
     * @param isAssigned the isAssigned to set
     */
    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    /**
     * Get the level.
     *
     * @return return the level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Set level.
     *
     * @param level the level to set
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}
