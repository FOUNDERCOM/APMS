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
angular.module('WebApp').controller('SalaryListCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {

    $scope.condition = {isEnabled: true, station: {bureau: {id: $rootScope.token['user']['org']['bureau']['id']}}};
    $listService.init($scope, {
        "controller": "AuxController",
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

    $scope.changeSalary = function(aux) {
        $ajaxCall.post({
            data : {
                controller: "AuxController",
                method: 'changeSalary',
                id: aux.id,
                salaryBase: aux.salaryBase,
                salaryBonus: aux.salaryBonus,
                salaryTax: aux.salaryTax,
                salarySSS: aux.salarySSS,
                salarySFund: aux.salarySFund,
                salaryCSS: aux.salaryCSS,
                salaryCFund: aux.salaryCFund,
                salarySGet: aux.salarySGet,
                salaryCPay: aux.salaryCPay
            },
            success: function() {
                $scope.load();
            }
        });
    };

    $scope.computeSalary = function(aux) {
        aux.salarySGet = parseInt(aux.salaryBase) + parseInt(aux.salaryBonus) - parseInt(aux.salaryTax) - parseInt(aux.salarySSS) - parseInt(aux.salarySFund);
        aux.salaryCPay = parseInt(aux.salaryBase) + parseInt(aux.salaryBonus) + parseInt(aux.salaryCSS) + parseInt(aux.salaryCFund);
    };
}]);
