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

package com.founder.bj.apms.com;

import com.lee.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: Client IP resolver.<br>
 * Created by Jimmybly Lee on 2017/10/5.
 *
 * @author Jimmybly Lee
 */
@SuppressWarnings({"CheckStyle", "unused"})
public abstract class ClientIPUtils {

    /**
     * Get client ip by {@link HttpServletRequest}.
     *
     * @param req client request
     * @return ip address string
     */
    public static String getClientIp(HttpServletRequest req) {
        // 1. check http header : 'x-forwarded-for'
        String clientIp = req.getHeader("x-forwarded-for");
        // 2. check http header : 'Proxy-Client-IP'
        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = req.getHeader("Proxy-Client-IP");
        }
        // 3. check http header : 'WL-Proxy-Client-IP'
        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = req.getHeader("WL-Proxy-Client-IP");
        }
        // 4. other
        if (StringUtils.isEmpty(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = req.getRemoteAddr();
        }
        if (clientIp.contains(",")) {
            String[] ips = clientIp.split(",");
            for (String ip : ips) {
                if (!"unknown".equalsIgnoreCase(ip)) {
                    clientIp = ip;
                    break;
                }
            }
        }
        return clientIp;
    }
}
