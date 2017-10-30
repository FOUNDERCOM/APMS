/**
 * Project Name : jwaf-token-api <br>
 * File Name : Org.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */

package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : Org <br>
 * Description : API of Token organization <br>
 * Create Time : 2016-09-18 <br>
 * @author jimmyblylee@126.com
 */
public interface Org extends Serializable {

    /**
     * @return the id.
     */
    String getId();

    /**
     * @param id the id to set.
     */
    void setId(String id);

    /**
     * @return the code.
     */
    String getCode();

    /**
     * @param code the code to set.
     */
    void setCode(String code);

    /**
     * @return the name.
     */
    String getName();

    /**
     * @param name the name to set.
     */
    void setName(String name);
}
