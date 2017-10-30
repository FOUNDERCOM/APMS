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

import com.lee.jwaf.token.Dict;
import com.lee.jwaf.token.Org;
import com.lee.jwaf.token.User;

/**
 * Description: Dto for {@link com.founder.bj.apms.sys.entity.SysUser}.<br>
 * Created by Jimmybly Lee on 2017/9/28.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings("unused")
public class UserDTO implements User, Serializable {

    private static final long serialVersionUID = 8097971841099343594L;

    /** Id. */
    private String id;
    /** Station. */
    private Org org;
    /** Name. */
    private String name;

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
     * Get the org.
     *
     * @return return the org
     */
    @Override
    public Org getOrg() {
        return org;
    }

    /**
     * Set org.
     *
     * @param org the org to set
     */
    @Override
    public void setOrg(Org org) {
        this.org = org;
    }

    /**
     * Get the name.
     *
     * @return return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * No impl.
     * @deprecated no impl
     * @return null
     */
    @Override
    @Deprecated
    public String getAccount() {
        return null;
    }

    /**
     * No impl.
     * @deprecated no impl
     */
    @Override
    @Deprecated
    public void setAccount(String account) {
    }

    /**
     * No impl.
     * @deprecated no impl
     * @return null
     */
    @Override
    @Deprecated
    public Dict getType() {
        return null;
    }

    /**
     * No impl.
     * @deprecated no impl
     */
    @Override
    @Deprecated
    public void setType(Dict dict) {

    }
}
