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
 * Description: WebApp Main Script.<br>
 * Created by Jimmybly Lee on 2017/9/10.
 * @author Jimmybly Lee
 */

/* Web App */
angular.module("WebApp", [
    "ui.router",
    "ui.bootstrap",
    "oc.lazyLoad",
    "ngSanitize",
    "ui.select",
    "mgcrea.ngStrap.tooltip",
    "mgcrea.ngStrap.datepicker",

    "chieffancypants.loadingBar",
    "frapontillo.bootstrap-switch"
]);

/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
angular.module("WebApp").config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        // global configs go here
    });
}]);

//AngularJS v1.3.x workaround for old style controller declarition in HTML
angular.module("WebApp").config(['$controllerProvider', function($controllerProvider) {
    // this option might be handy for migrating old apps, but please don't use it
    // in new ones!
    $controllerProvider.allowGlobals();
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
 *********************************************/

/* Setup global settings */
angular.module("WebApp").factory('settings', ['$rootScope', function($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        assetsPath: 'resources',
        globalPath: 'resources/global',
        layoutPath: 'resources/layouts/layout4'
    };

    $rootScope.settings = settings;

    return settings;
}]);

/* Setup Routing For All Pages */
angular.module("WebApp").config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    $.ajaxSettings.async = false;
    var cacheVersion = "?" + $("html").attr("cacheVersion");

    // 配置路由跳转相关配置信息
    $stateProvider.state("home", {
        url: "/home.html",
        templateUrl: "packages/home/view/home.html" + cacheVersion,
        data: {
            "nav": ["首页"],
            "isBuiltIn": true,
            "pageTitle": "首页",
            "pageSubTitle": "辅警管理系统"
        },
        controller: "HomeCtrl",
        resolve: {
            deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                return $ocLazyLoad.load({
                    name: "WebApp",
                    files: ["packages/home/js/HomeCtrl.js" + cacheVersion,"packages/index/css/coming-soon.css" + cacheVersion]
                });
            }]
        }
    });
    $.getJSON("packages/config.json" + cacheVersion, function (configs) {
        $.each(configs, function (name, cfg) {
            $.getJSON(cfg["url"] + cacheVersion, function (states) {
                $.each(states, function (key, data) {
                    var files = [];
                    $.each(data["files"], function(idx, url) {
                        files.push(url + cacheVersion);
                    });
                    $stateProvider.state(key, {
                        url: "/" + key + ".html",
                        templateUrl: data["templateUrl"] + cacheVersion,
                        data: data["data"],
                        controller: data["controller"],
                        resolve: {
                            deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                                return $ocLazyLoad.load({
                                    name: "WebApp",
                                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
                                    files: files
                                });
                            }]
                        }
                    });
                });
            });
        });
    });

    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/404.html");

    $.ajaxSettings.async = true;
}]);

/* Init global settings and run the app */
angular.module("WebApp").run(["$rootScope", "settings", "$state", function($rootScope, settings, $state) {
    $rootScope.cacheVersion = "?" + $("html").attr("cacheVersion");
    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view
    // 一些业务上可能的默认配置
    $.getJSON("packages/index/json/config.json", function(obj) {
        $rootScope.cfg = obj;
    });
}]);
