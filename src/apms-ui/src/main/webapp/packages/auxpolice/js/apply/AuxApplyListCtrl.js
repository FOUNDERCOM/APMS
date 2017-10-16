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
angular.module('WebApp').controller('AuxApplyListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {

    // 流程相关颜色和消息配置
    $.getJSON("packages/auxpolice/js/com/config.json", function(data) {
        $scope.cfg = data;
    });
    // 状态字典列表
    $ajaxCall.getDictList($scope, "PROCESS_STATUS", 'statusList');

    // 性别字典列表
    $ajaxCall.getDictList($scope, "SEX", 'sexList');
    // 民族字典列表
    $ajaxCall.getDictList($scope, "NATION", 'nationList');
    // 健康状况字典列表
    $ajaxCall.getDictList($scope, "HEALTH", 'healthList');
    // 健康状况字典列表
    $ajaxCall.getDictList($scope, "POLITICAL_STATUS", 'politicalStatusList');
    // 学位字典列表
    $ajaxCall.getDictList($scope, "EDUCATION_DEGREE", 'eduDegreeList');

    // 分局列表
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

    // 科所队列表
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

    $scope.condition = {
        isEnabled: true,
        station: {bureau: {id: $rootScope.token['user']['org']['bureau']['id']}},
        status: {code : 'TO_APPLY', value: "待提交"}
    };
    $listService.init($scope, {
        "controller": "AuxController",
        "method": "query",
        callback: function (success) {
            $scope.list = success.data.result;
        },
        pageSizeList: [6, 12, 18, 24],
        pageSize: 6
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
            message: isEnabled ? "是否确认恢复该辅警？" : "是否确认注销该辅警？",
            buttons: {
                main: {label: " 取 消 ", className: "dark icon-ban btn-outline"},
                danger: {
                    label: isEnabled ? " 确  定 ！ " : " 注 销 ！",
                    className: isEnabled ? "fa fa-recycle green" : "fa fa-ban red",
                    callback: function () {
                        $ajaxCall.post({
                            data: {
                                controller: "AuxController",
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
        var divId = "modifyAuxploiceApplyModalDiv";
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
            "addProvince": "",
            "addCity": "",
            "addCountry": "",
            "addDetail": "",
            "postCode": "",
            "bureau": {},
            "station": {},
            "photo": $rootScope.cfg['defaultPhoto'],
            "awardList": [],
            "eduList": [],
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
        var divId = "modifyAuxploiceApplyModalDiv";
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
        var divId = "viewAuxploiceApplyModalDiv";
        var scope = $("#" + divId).scope();
        scope.title = "查看辅警信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    /**
     * 执行流程操作
     * @param item entity 节点
     * @param method 方法
     */
    $scope.take = function(item, method) {
        $ajaxCall.post({
            data : {
                controller: "AuxController",
                method: method,
                id: item.id
            },
            success: function() {
                $scope.load();
            }
        });
    }
}]);
