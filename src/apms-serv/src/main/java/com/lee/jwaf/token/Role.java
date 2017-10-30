/**
 * Project Name : jwaf-token-api <br>
 * File Name : Role.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */

package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : Role <br>
 * Description : api of Token Role <br>
 * Create Time : 2016-09-18 <br>
 * @author jimmyblylee@126.com
 */
public interface Role extends Serializable {

    /**
     * @return the id.
     */
    Integer getId();

    /**
     * @param id the id to set.
     */
    void setId(Integer id);

    /**
     * @return the name.
     */
    String getName();

    /**
     * @param name the name to set.
     */
    void setName(String name);

    /**
     * @return the type.
     */
    Dict getType();

    /**
     * @param type the type to set.
     */
    void setType(Dict type);
}
