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

/**
 * Description: Session 超时.<br>
 * Created by Jimmybly Lee on 2017/11/3.
 * @author Jimmybly Lee
 */
var SessionTimeout = function () {

    var handlesessionTimeout = function () {
        $.sessionTimeout({
            title: '提示',
            message: '您的会话即将超时',
            logoutButton: '退出',
            keepAliveButton: '请保持连接',
            keepAliveUrl: 'mvc/dispatch?controller=LoginController&method=keepAlive',
            redirUrl: 'login.html',
            logoutUrl: 'login.html',
            warnAfter: 600000, // warn after 5 seconds
            redirAfter: 15000, // redirect after 10 seconds,
            ignoreUserActivity: true,
            countdownMessage: '即将在 {timer} 秒后自动退出.',
            countdownBar: true
        });
    };

    return {
        //main function to initiate the module
        init: function () {
            handlesessionTimeout();
        }
    };

}();
$(document).ready(function() {
    SessionTimeout.init();
});
