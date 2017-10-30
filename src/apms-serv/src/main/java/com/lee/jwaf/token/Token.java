/**
 * Project Name : jwaf-token-api <br>
 * File Name : Token.java <br>
 * Package Name : com.lee.jwaf.token <br>
 * Create Time : 2016-09-18 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */

package com.lee.jwaf.token;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName : Token <br>
 * Description : api of Token <br>
 * Create Time : 2016-09-18 <br>
 * @author jimmyblylee@126.com
 */
public interface Token extends Serializable {

    User user();

    Org org();

    List<Func> funcs();

    FuncTree funcTree();

    List<Role> roles();

    List<Group> groups();
}
