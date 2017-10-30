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

import java.io.Serializable;

import com.founder.bj.apms.sys.entity.SysUser;

/**
 * Description: Aux Stuff info api. such as award, work, education...<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
public interface AuxStuff extends Serializable {

    /**
     * Get Id.
     * @return the entity id.
     */
    String getId();

    /**
     * Set id
     * @param id the entity id.
     */
    void setId(String id);

    /**
     * Get {@link AuxInfo} of the stuff entity.
     * @return the {@link AuxInfo}
     */
    AuxInfo getAux();

    /**
     * Set {@link AuxInfo}.
     * @param aux the {@link AuxInfo}
     */
    void setAux(AuxInfo aux);

    /**
     * Get the lastUpdateUser.
     *
     * @return return the lastUpdateUser
     */
    SysUser getLastUpdateUser();

    /**
     * Set lastUpdateUser.
     *
     * @param lastUpdateUser the lastUpdateUser to set
     */
    void setLastUpdateUser(SysUser lastUpdateUser);

    /**
     * Get the lastUpdateDate.
     *
     * @return return the lastUpdateDate
     */
    String getLastUpdateDate();

    /**
     * Set lastUpdateDate.
     *
     * @param lastUpdateDate the lastUpdateDate to set
     */
    void setLastUpdateDate(String lastUpdateDate);

    /**
     * Get the lastUpdateIp.
     *
     * @return return the lastUpdateIp
     */
    String getLastUpdateIp();

    /**
     * Set lastUpdateIp.
     *
     * @param lastUpdateIp the lastUpdateIp to set
     */
    void setLastUpdateIp(String lastUpdateIp);
}
