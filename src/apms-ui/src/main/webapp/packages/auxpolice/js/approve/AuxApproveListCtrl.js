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
 * Description: TODO.<br>
 * Created by Jimmybly Lee on 2017/10/10.
 * @author Jimmybly Lee
 */

angular.module('WebApp').controller('AuxApproveListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {

    // 流程相关颜色和消息配置
    $.getJSON("packages/auxpolice/js/com/config.json", function(data) {
        $scope.cfg = data;
    });
    // 状态字典列表
    $ajaxCall.getDictList($scope, "PROCESS_STATUS", 'statusList');

    $scope.condition = {
        isEnabled: true,
        station: {bureau: {id: $rootScope.token['user']['org']['bureau']['id']}}
    };

    $listService.init($scope, {
        "controller": "AuxController",
        "method": "query",
        callback: function (success) {
            $scope.list = success.data.result;
        },
        pageSizeList: [12, 18, 24, 48],
        pageSize: 12
    });

    /**
     * 刷新数据
     */
    $scope.load = function () {
        $scope.pageRequest.getResponse();
    };
    $scope.load();


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
            	alert("数据操作成功！");
                $scope.load();
            }
        });
    }
}]);
