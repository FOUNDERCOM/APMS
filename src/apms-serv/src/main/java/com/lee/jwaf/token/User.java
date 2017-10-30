/**
 * Project Name : jwaf-token-api <br>
 * File Name : User.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */

package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : User <br>
 * Description : api of Token user <br>
 * Create Time : 2016-09-18 <br>
 * @author jimmyblylee@126.com
 */
public interface User extends Serializable {

    /**
     * @return the id.
     */
    String getId();

    /**
     * @param id the id to set.
     */
    void setId(String id);

    /**
     * @return the org.
     */
    Org getOrg();

    /**
     * @param org the dept to set.
     */
    void setOrg(Org org);

    /**
     * @return the name.
     */
    String getName();

    /**
     * @param name the name to set.
     */
    void setName(String name);

    /**
     * @return the account.
     */
    String getAccount();

    /**
     * @param account the account to set.
     */
    void setAccount(String account);

    /**
     * @return the type.
     */
    Dict getType();

    /**
     * @param type the type to set.
     */
    void setType(Dict type);
}
