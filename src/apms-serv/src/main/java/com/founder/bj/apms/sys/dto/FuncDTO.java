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
import java.util.List;

import com.lee.jwaf.token.Dict;
import com.lee.jwaf.token.FuncTree;

/**
 * Description: 功能（菜单）数据传输类型.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
public class FuncDTO implements FuncTree, Serializable {

    /** SerialVersionUID. */
    private static final long serialVersionUID = 3353231166287810537L;

    // CSOFF: JavadocVariable
    private String id;
    private String code;
    private String name;
    private Dict type;
    private String url;
    private Integer seq;
    private String icon;
    private String parentId;
    private Boolean isVisible;

    private List<FuncTree> children;
    private Boolean isRoot;
    private Boolean isLeaf;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getSeq() {
        return seq;
    }

    @Override
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public List<FuncTree> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<FuncTree> children) {
        this.children = children;
    }

    @Override
    public Boolean getIsRoot() {
        return isRoot;
    }

    @Override
    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public Boolean getIsLeaf() {
        return isLeaf;
    }

    @Override
    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
    public Boolean getIsVisible() {
        return isVisible;
    }

    @Override
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public Dict getType() {
        return type;
    }

    @Override
    public void setType(Dict type) {
        this.type = type;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}
