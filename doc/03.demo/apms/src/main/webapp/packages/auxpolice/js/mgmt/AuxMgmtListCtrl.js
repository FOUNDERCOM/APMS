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
 * Description: 辅警管理列表控制器.<br>
 * Created by Jimmybly Lee on 2017/9/17.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('AuxMgmtListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {


    /**
     * 修改给定实体的状态
     * @param item 给定实体
     * @param isEnabled 新状态
     */
    $scope.changeStatus = function (item, isEnabled) {
        bootbox.dialog({
            title: "请确认",
            message: isEnabled ? "是否确认恢复该辅警？" : "是否确认注销该辅警？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: isEnabled ? " 恢 复 ！ " : " 注 销 ！",
                    className: isEnabled ? "fa fa-recycle green" : "fa fa-ban red",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "ZJInfoController",
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
        var divId = "modifyAuxploiceMgmtModalDiv";
        var scope = $("#" + divId).scope();
        scope.title = "注册辅警信息";
        scope.method = "create";
        scope.entity = {
            "name": "",
            "tel": "",
            "mobile": "",
            "job": "",
            "identityCard": "",
            "joinDate": "",
            "standing": 0,
            "sex": {},
            "nation": {},
            "health": {},
            "politicalStatus": {},
            "educationDegree": {},
            "institutions": "",
            "major": "",
            "birthday": "",
            "nativePlace": "",
            "resume": "",
            "addProvince": {},
            "addCity": {},
            "addCountry": {},
            "addDetail": "",
            "postCode": "",
            "bureau": {},
            "station": {},
            "photo": "resources/layouts/layout4/img/default-user.png",
            "awardList": [],
            "educationList": [],
            "familyList": [],
            "punishList": [],
            "workList": []
        };

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    /**
     * 准备修改实体
     */
    $scope.prepareToModify = function (item) {
        var divId = "modifyAuxploiceMgmtModalDiv";
        var scope = $("#" + divId).scope();
        scope.title = "修改辅警信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    /**
     * 准备查看实体
     */
    $scope.prepareToView = function (item) {
        var divId = "viewAuxploiceMgmtModalDiv";
        var scope = $("#" + divId).scope();
        scope.title = "查看辅警信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    /** make demo data */
    $scope.demo = function() {
        $.getJSON("packages/auxpolice/js/mgmt/demo.json", function(obj) {
            $scope.pageResponse = obj;
            $scope.list = obj.result;
        });
    };
    $scope.load = function() {
        $scope.demo();
    };
    $scope.load();
}]);
