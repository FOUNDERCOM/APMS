/**
 * Project Name : jwaf-token-api <br>
 * File Name : AuthorizationTokenService.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */

package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : AuthorizationTokenService <br>
 * Description : Application authorization service, provide Token for system <br>
 * Create Time : 2016-09-18 <br>
 * @author jimmyblylee@126.com
 */
public interface AuthorizationTokenService extends Serializable {

    /**
     * Description : fetch user token by given user id <br>
     * Create Time: 2016-09-18 <br>
     * Create by : jimmyblylee@126.com <br>
     *
     * @param userid the user id
     * @return the user's token
     * @throws Throwable if can not find eht user by given id
     */
    Token getTokenByUserId(Integer userid) throws Throwable;
}
