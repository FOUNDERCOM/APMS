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
 * Description: 更新字典控制器.<br>
 * Created by Jimmybly Lee on 2017/7/3.
 * @author Jimmybly Lee
 */
angular.module('WebApp').controller('DictModifyCtrl', ['$scope', "$ajaxCall", function ($scope, $ajaxCall) {

    /**
     * 提交表单
     */
    $scope.submit = function () {
        $ajaxCall.post({
            data: {
                controller: "DictController",
                method: $scope.method,
                entity: JSON.stringify($scope.entity)
            },
            success: function () {
                $scope.$emit("submitted");
            }
        });
    };

    /**
     * 初始化下拉菜单
     */
    // $ajaxCall.post({
    //     data: {
    //         controller: "DictController",
    //         method: "query",
    //         start: 0,
    //         limit: 100,
    //         condition: JSON.stringify({
    //             isEnabled: true,
    //             isNature: true
    //         })
    //     },
    //     success: function(data) {
    //         $scope.natureList = data.result;
    //     }
    // });
}]);
