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
 * Description: service.<br>
 * Created by Jimmybly Lee on 2017/6/30.
 * @author Jimmybly Lee
 */
angular.module("WebApp").service("$listService", ["$http", function($http) {
    var $scopeLocal = {};
    // default pagination configuration list
    var pageSizeList = [5, 10, 30, 50];
    // default pagination options
    var defaultOptions = {
        pageSize: pageSizeList[0],
        queryConditionKey: "condition",
        queryConditionObj: "condition",
        callback: function() {},
        error: function() {}
    };
    this.init = function($scope, option) {
        var options = $.extend({}, defaultOptions, option);
        // $scopeLocal = $scope;
        $scope.pageSizeList = option.pageSizeList ? option.pageSizeList : pageSizeList;
        $scope.pageRequest = {"pageNum": 1, "pageSize": options.pageSize};
        $scope.pageRequest.getResponse = function() {
            var requestData = {
                "controller": options.controller,
                "method": options.method,
                "start": ($scope.pageRequest.pageNum - 1) * $scope.pageRequest.pageSize,
                "limit": $scope.pageRequest.pageSize
            };
            requestData = $.extend({}, $scope[options.queryConditionObj], requestData);
            requestData[options.queryConditionKey] = JSON.stringify($scope[options.queryConditionObj]);

            $http({
                method : 'post',
                url : 'mvc/dispatch',
                data : requestData,
                headers : {
                    'Content-Type' : 'application/x-www-form-urlencoded'
                },
                transformRequest : function(obj) {
                    var str = [];
                    $.each(obj, function(idx, data){
                        str.push(encodeURIComponent(idx) + "=" + encodeURIComponent(data));
                    });
                    return str.join("&");
                }
            }).then(function(success) {
                $scope.pageResponse = success.data;
                options.callback(success);
            }, options.error);
        };

        $scopeLocal = $scope;
    };
}]);
angular.module("WebApp").service("$ajaxCall", ["$http", function($http){
    this.post = function(param) {
        $http({
            method : param.method ? param.method : 'post',
            url : param.url ? param.url : 'mvc/dispatch',
            data : param.data,
            headers : {
                'Content-Type' : 'application/x-www-form-urlencoded'
            },
            transformRequest : function(obj) {
                var str = [];
                $.each(obj, function(idx, data){
                    str.push(encodeURIComponent(idx) + "=" + encodeURIComponent(data));
                });
                return str.join("&");
            }
        }).success(param.success ? param.success : $.noop).error(param.error ? param.error : $.noop);
    };
    this.getDictList = function(scope, nature, key) {
        this.post({
            data: {
                controller: "DictController",
                method: "query",
                condition: JSON.stringify({nature: nature, isNature: false, isEnabled: true}),
                start: 0,
                limit: 100
            },
            success: function(res) {
                scope[key] = res.result;
            }
        });
    };
}]);
