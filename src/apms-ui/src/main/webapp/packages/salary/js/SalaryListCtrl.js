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
	if($rootScope.token['user']['org']['bureau']['id'] == $rootScope.token['user']['org']['id']){
		$scope.condition = {isEnabled: "1"};
	}else if($rootScope.token['user']['org']['isManage']){
		$scope.condition = {isEnabled: "1", station: {bureau: {id: $rootScope.token['user']['org']['bureau']['id']}}};
	}else{
		$scope.condition = {isEnabled: "1", station: {id: $rootScope.token['user']['org']['id']}};
	}
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
    	item.appraiseList.sort(function(a, b){
            return parseInt(a.num)-parseInt(b.num);  
        });
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
    	if(aux.salaryBase === undefined || aux.salaryBase === null){
    		alert("请输入辅警的基本工资！");
            return;
    	}else if(aux.salaryJfly === undefined || aux.salaryJfly === null || aux.salaryJfly === "null"){
    		alert("请选择工资经费来源！");
            return;
    	}else if(aux.salarySGet === undefined || aux.salarySGet === null){
    		alert("请输入辅警的实付工资！");
            return;
    	}else if(aux.salaryCPay === undefined || aux.salaryCPay === null){
    		alert("请输入辅警的公付总额！");
            return;
    	}else{
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
                    salarySSW: aux.salarySSW,
                    salarySSY: aux.salarySSY,
                    salarySGS: aux.salarySGS,
                    salarySYW: aux.salarySYW,
                    salaryCFund: aux.salaryCFund,
                    salarySGet: aux.salarySGet,
                    salaryCPay: aux.salaryCPay,
                    salaryGwgz: aux.salaryGwgz,
                    salaryGlgz: aux.salaryGlgz,
                    salaryZjgz: aux.salaryZjgz,
                    salaryJtgz: aux.salaryJtgz,
                    salaryJfly: aux.salaryJfly
                },
                success: function() {
                    $scope.load();
                }
            });
    	}
    };

    $scope.computeSalary = function(aux) {
        aux.salarySGet = parseInt(aux.salaryBase) + parseInt(aux.salaryBonus) - parseInt(aux.salaryTax) - parseInt(aux.salarySSS) - parseInt(aux.salarySFund);
        aux.salaryCPay = parseInt(aux.salaryBase) + parseInt(aux.salaryBonus) + parseInt(aux.salaryCSS) + parseInt(aux.salaryCFund);
    };
}]);
