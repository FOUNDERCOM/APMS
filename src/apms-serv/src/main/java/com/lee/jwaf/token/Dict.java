/**
 * Project Name : jwaf-token-api <br>
 * File Name : Dict.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */

package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : Dict <br>
 * Description : API of Token dictionary <br>
 * Create Time : 2016-09-18 <br>
 * @author jimmyblylee@126.com
 */
public interface Dict extends Serializable {

    /**
     * @return the nature.
     */
    String getNature();

    /**
     * @param nature the nature to set.
     */
    void setNature(String nature);

    /**
     * @return the code.
     */
    String getCode();

    /**
     * @param code the code to set.
     */
    void setCode(String code);

    /**
     * @return the value.
     */
    String getValue();

    /**
     * @param value the value to set.
     */
    void setValue(String value);
}
