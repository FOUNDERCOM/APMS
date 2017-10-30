/**
 * Project Name : jwaf-token-api <br>
 * File Name : Func.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */

package com.lee.jwaf.token;

import java.io.Serializable;

/**
 * ClassName : Func <br>
 * Description : api of Token function <br>
 * Create Time : 2016-09-18 <br>
 * @author jimmyblylee@126.com
 */
public interface Func extends Serializable {

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

    /**
     * @return the seq.
     */
    Integer getSeq();

    /**
     * @param seq the seq to set.
     */
    void setSeq(Integer seq);

    /**
     * @return the type.
     */
    Dict getType();

    /**
     * @param type the type to set.
     */
    void setType(Dict type);

    /**
     * @return the url.
     */
    String getUrl();

    /**
     * @param url the url to set.
     */
    void setUrl(String url);

    /**
     * @return the icon.
     */
    String getIcon();

    /**
     * @param icon the icon to set.
     */
    void setIcon(String icon);

    /**
     * @return the isVisible.
     */
    Boolean getIsVisible();

    /**
     * @param isVisible the isVisible to set.
     */
    void setIsVisible(Boolean isVisible);
}
