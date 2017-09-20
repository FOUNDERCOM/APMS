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
 * Description: 辅警注册列表控制器.<br>
 * Created by Jimmybly Lee on 2017/7/2.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('AuxApplyListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {

    // 流程相关颜色和消息配置
    $.getJSON("packages/com/js/config.json", function(data) {
        $scope.cfg = data;
    });

    /**
     * 流程相关操作
     * @param item 实体
     * @param task 任务
     */
    $scope.take = function (item, task) {
        $ajaxCall.post({
            data : {
                controller: "ZJInfoController",
                method: task,
                id: item.id
            },
            success: function() {
                $scope.load();
            }
        });
    };

    /* 与ZJGL一致 开始*/
    $scope.condition = {xt_qy: true, gz_gaxt:true, xt_sfkw: false};
    $listService.init($scope, {
        pageSizeList: [4, 6, 8, 12, 18, 24],
        pageSize: 4,
        "controller": "ZJInfoController",
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
    $scope.prepareToAdd = function (isKW) {
        var divId = "updateZJModalDiv";
        if (isKW) {
            divId = "updateZJKWModalDiv";
        }
        var scope = $("#" + divId).scope();
        scope.title = "注册辅警信息";
        scope.method = "create";
        scope.entity = {
            gz_gaxt: true,
            xt_sfkw: !!isKW,
            jb_zp: $rootScope.cfg ["defaultPhoto"],
            gzjlList: [],
            jlqkList: [],
            jybjList: [],
            jzList: [],
            yjcgList: [],

            zylbList: []
        };

        scope.$on("submitted", function () {
            $scope.load();
        });
    };

    /**
     * 准备修改实体
     */
    $scope.prepareToUpdate = function (item, isKW) {
        var divId = "updateZJModalDiv";
        if (isKW) {
            divId = "updateZJKWModalDiv";
        }
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
    $scope.prepareToView = function (item, isKW) {
        var divId = "viewZJModalDiv";
        if (isKW) {
            divId = "viewZJKWModalDiv";
        }
        var scope = $("#" + divId).scope();
        scope.title = "查看辅警信息";
        scope.method = "update";
        scope.entity = item;

        scope.$on("submitted", function () {
            $scope.load();
        });
    };
    /* 与ZJGL一致 结束*/

}]);
