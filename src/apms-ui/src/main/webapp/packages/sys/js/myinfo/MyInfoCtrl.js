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
 * Description: 我的信息修改.<br>
 * Created by Jimmybly Lee on 2017/10/24.
 * @author Jimmybly Lee
 */

angular.module('WebApp').controller('MyInfoCtrl', ['$rootScope', '$scope', "$ajaxCall", function ($rootScope, $scope, $ajaxCall) {

    $ajaxCall.post({
        data: {
            controller: "UserController",
            method: "query",
            condition: JSON.stringify({id: $rootScope["token"]["user"]["id"], isEnabled: true}),
            start: 0,
            limit: 1
        },
        success: function (success) {
            $scope.entity = success.result[0];
        }
    });

    /**
     * 提交表单
     */
    $scope.submit = function () {
        $ajaxCall.post({
            data: {
                controller: "UserController",
                method: "update",
                entity: JSON.stringify($scope.entity)
            },
            success: function () {
                bootbox.dialog({
                    title: "提示",
                    message: "更新成功",
                    buttons: {
                        main: {label: " 确 定 "}
                    }
                });
            }
        });
    };

    $scope.prepare2SetPhoto = function() {
        var uploadModalScope = $("#uploadPhoto").scope();
        uploadModalScope.init();
        uploadModalScope.$on("submit", function(event, data) {
            $scope.entity.photo.data = data;
        });
    };

    $scope.resetPassWord = function() {
        if ($scope.pwd === undefined || $scope.pwd.length === 0) {
            bootbox.alert({
                title: "提示",
                message: "密码不能为空",
                buttons: {
                    main: {label: "确定"}
                }
            });
        } else {
            bootbox.dialog({
                title: "请确认",
                message: "确认修改密码？",
                buttons: {
                    main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                    danger: {
                        label: " 确 认 ！ ",
                        className: "icon-key yellow-gold",
                        callback: function () {
                            $ajaxCall.post({
                                data: {
                                    controller: "UserController",
                                    method: "changePassword",
                                    entity: JSON.stringify({id: $scope.entity.id, account: $scope.entity.account.account, pwd: $scope.pwd})
                                },
                                success: function() {
                                    bootbox.alert({title: "提示", message: "密码已经修改完毕！"});
                                }
                            });
                        }
                    }
                }
            });
        }
    };
}]);
