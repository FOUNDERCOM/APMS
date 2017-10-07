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
 * Description: 用户列表控制器.<br>
 * Created by Jimmybly Lee on 2017/7/2.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('UserallListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {
    $scope.showTable = true;

    $ajaxCall.post({
        data : {
            controller: "DeptBureauController",
            method: 'query',
            condition : JSON.stringify({isEnabled: true}),
            start: 0,
            limit: -1
        },
        success: function(res) {
            $scope.bureauList = res['result'];
        }
    });

    $ajaxCall.post({
        data : {
            controller: "DeptStationController",
            method: 'query',
            condition : JSON.stringify({isEnabled: true}),
            start: 0,
            limit: -1
        },
        success: function(res) {
            $scope.stationList = res['result'];
        }
    });
    $scope.condition = {isEnabled: true};
    $listService.init($scope, {
        "controller": "UserController",
        "method": "query",
        callback: function (success) {
            $scope.list = success.data.result;
        }
    });

    /**
     * 刷新数据
     */
    $scope.load = function () {
        $scope.pageRequest.getResponse();
    };
    $scope.load();

    /**
     * 修改给定实体的状态
     * @param item 给定实体
     * @param isEnabled 新状态
     */
    $scope.changeStatus = function (item, isEnabled) {
        bootbox.dialog({
            title: "请确认",
            message: isEnabled ? "是否确认恢复该用户？" : "是否确认禁用该用户？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: isEnabled ? " 恢 复 ！ " : " 禁 用 ！",
                    className: isEnabled ? "fa fa-recycle green" : "fa fa-ban red",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "UserController",
                                method: isEnabled ? "resume" : "remove",
                                id: item.id
                            },
                            success: function () {
                                $scope.load();
                            }
                        });
                    }
                }
            }
        });
    };

    /**
     * 准备添加实体
     */
    $scope.prepareToAdd = function () {
        var scope = $("#modifyUserallModalDiv").scope();
        scope.title = "添加用户信息";
        scope.method = "create";
        scope.entity = {
            station: {
                id: $rootScope.token['user']['org']['id'],
                name: $rootScope.token['user']['org']['name'],
                bureau: {
                    id: $rootScope.token['user']['org']['bureau']['id'],
                    name: $rootScope.token['user']['org']['bureau']['name']
                }
            },
            account: {
                pwd: "12345"
            },
            photo: {
                data: $rootScope.cfg ["defaultPhoto"]
            }
        };

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    /**
     * 准备修改实体
     */
    $scope.prepareToUpdate = function (item) {
        var scope = $("#modifyUserallModalDiv").scope();
        scope.title = "修改用户信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    $scope.resetPwd = function(item) {
        bootbox.dialog({
            title: "请确认",
            message: "是否为" + item.name + "重置密码？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: " 重 置 ！ ",
                    className: "icon-key yellow-gold",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "UserController",
                                method: "changePassword",
                                entity: JSON.stringify({id: item.id, account: item.account.account, pwd: "12345"})
                            },
                            success: function() {
                                bootbox.alert({title: "提示", message: "密码已经重置完毕，重置为12345"});
                            }
                        });
                    }
                }
            }
        });
    };
}]);
