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

package com.founder.bj.apms.auxpolice.service;

import com.lee.jwaf.token.Token;

/**
 * Description: Aux Flow Service.<br>
 * Created by Jimmybly Lee on 2017/10/9.
 *
 * @author Jimmybly Lee
 */
public interface AuxFlowService {

    /**
     * Apply.
     * @param token token
     * @param ip ip
     * @param id {@link com.founder.bj.apms.auxpolice.entity.AuxInfo} id.
     */
    void apply(Token token, String ip, String id);

    /**
     * Accept.
     * @param token token
     * @param ip ip
     * @param id {@link com.founder.bj.apms.auxpolice.entity.AuxInfo} id.
     */
    void accept(Token token, String ip, String id);

    /**
     * Pass.
     * @param token token
     * @param ip ip
     * @param id {@link com.founder.bj.apms.auxpolice.entity.AuxInfo} id.
     */
    void pass(Token token, String ip, String id);

    /**
     * Reject.
     * @param token token
     * @param ip ip
     * @param id {@link com.founder.bj.apms.auxpolice.entity.AuxInfo} id.
     */
    void reject(Token token, String ip, String id);
}
