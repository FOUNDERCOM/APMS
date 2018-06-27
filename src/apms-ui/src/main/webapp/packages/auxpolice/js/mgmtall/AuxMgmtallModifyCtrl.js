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
 * Description: 辅警管理修改控制器.<br>
 * Created by Jimmybly Lee on 2017/9/17.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('AuxMgmtallModifyCtrl', ['$rootScope', '$scope', "$listService", "$ajaxCall", function ($rootScope, $scope, $listService, $ajaxCall) {
	/**
     * 离职保存
     */
    $scope.removeAux = function(){
        if ($scope.entity["addRyzt"] === "" || $scope.entity["addRyzt"] === null || $scope.entity["addRyzt"] === "在职") {
        	alert("请选择离职原因！");
            return;
        }
    	$ajaxCall.post({
            data: {
                controller: "AuxController",
                method: "remove",
                id: $scope.entity.id,
                ryzt: $scope.entity.addRyzt
            },
            success: function () {
            	alert("注销辅警数据成功！");
            	$("#removeAuxDiv").modal('hide');
                $scope.load();
            }
        });
    };
}]);
